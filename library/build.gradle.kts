import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.plugin.compose)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.maven.publish)
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.wanted.android.wanted.design.resources"
}

kotlin {
    androidTarget {
        publishLibraryVariants("release")
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
            freeCompilerArgs.addAll(
                listOf(
                    "-opt-in=kotlin.ExperimentalUnsignedTypes",
                    "-opt-in=kotlinx.coroutines.FlowPreview",
                    "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "-opt-in=kotlinx.coroutines.InternalCoroutinesApi",
                    "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
                    "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
                    "-opt-in=androidx.compose.runtime.ExperimentalComposeApi",
                    "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
                    "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
                    "-opt-in=androidx.compose.foundation.layout.ExperimentalLayoutApi",
                    "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
                )
            )
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(libs.compose.components.resources)
                implementation(libs.compottie)
                implementation(libs.coil.compose)
                implementation(libs.coil.network.ktor3)
            }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
            implementation(kotlin("stdlib"))

            implementation(libs.androidx.appcompat)
            implementation(libs.androidx.core)
            implementation(libs.androidx.vectordrawable)
            implementation(libs.androidx.constraintlayout)
            implementation(libs.androidx.constraintlayout.compose)
            implementation(libs.google.material)

            implementation(libs.ktor.client.android)
        }

        val iosX64Main by getting {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }

        val iosArm64Main by getting {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }

        val iosSimulatorArm64Main by getting {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
    }
}

android {
    namespace = "com.wanted.android.designsystem"
    compileSdk = 35
    buildToolsVersion = "35.0.1"

    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    publishing {
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    packaging {
        resources {
            excludes += listOf(
                "/META-INF/{AL2.0,LGPL2.1}",
                "/META-INF/LICENSE.md",
                "/META-INF/LICENSE-notice.md"
            )
        }
    }

    lint {
        abortOnError = false
        checkReleaseBuilds = false
        ignoreWarnings = true
        disable += setOf(
            "ObsoleteLintCustomCheck",
            "LintError"
        )
        warningsAsErrors = false
    }
}

dependencies {
    add("androidMainImplementation", platform(libs.androidx.compose.bom))
    add("androidMainImplementation", libs.androidx.compose.ui)
    add("androidMainImplementation", libs.androidx.compose.material)
    add("androidMainImplementation", libs.androidx.compose.material3)
    add("androidMainImplementation", libs.androidx.compose.material.icons.extended)
    add("androidMainImplementation", libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
}

val publishPropertiesFile = file("publish.properties")
val publishProperties = Properties()
if (publishPropertiesFile.exists()) {
    publishPropertiesFile.inputStream().use { fis -> publishProperties.load(fis) }
}

group = publishProperties.getProperty("groupId") ?: "run.thespot.montage"
version = (findProperty("version") as String?)
    ?: publishProperties.getProperty("version")
    ?: "0.1.0-SNAPSHOT"

val libArtifactId = publishProperties.getProperty("artifactId") ?: "montage-kmp"

val buildAar by tasks.registering {
    group = "build"
    description = "Builds the release AAR file"
    dependsOn("assembleRelease")

    doLast {
        val aarFile = file("build/outputs/aar/library-release.aar")
        if (aarFile.exists()) {
            println("AAR built successfully:")
            println("Location: ${aarFile.absolutePath}")
            println("Size: ${aarFile.length() / 1024} KB")
        } else {
            println("AAR file not found")
        }
    }
}

afterEvaluate {
    tasks.matching {
        it.name.contains("lint", ignoreCase = true)
    }.configureEach {
        enabled = false
    }

    tasks.named("buildKotlinToolingMetadata").configure {
        enabled = true
    }

    // Ensure the Kotlin tooling metadata artifact exists before KMP publication validation/upload.
    tasks.matching {
        it.name == "generateMetadataFileForKotlinMultiplatformPublication" ||
            it.name == "publishKotlinMultiplatformPublicationToMavenCentralRepository" ||
            it.name == "publishAndReleaseToMavenCentral"
    }.configureEach {
        dependsOn("buildKotlinToolingMetadata")
    }
}

mavenPublishing {
    publishToMavenCentral(automaticRelease = true)
    signAllPublications()

    coordinates(
        group.toString(),
        libArtifactId,
        version.toString()
    )

    pom {
        name.set("Montage KMP Design System")
        description.set("Montage design system library prepared for Kotlin Multiplatform migration")
        url.set("https://github.com/run-spot/montage-kmp")

        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/licenses/MIT")
                distribution.set("https://opensource.org/licenses/MIT")
            }
        }

        developers {
            developer {
                id.set("runthespot")
                name.set("RunTheSpot")
                url.set("https://github.com/run-spot")
            }
        }

        scm {
            url.set("https://github.com/run-spot/montage-kmp")
            connection.set("scm:git:git://github.com/run-spot/montage-kmp.git")
            developerConnection.set("scm:git:ssh://git@github.com/run-spot/montage-kmp.git")
        }
    }
}

apply(from = "gradle/documentation.gradle.kts")
