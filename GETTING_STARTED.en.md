# 🎨 Montage Android

**A Modern Android Design System Built with Jetpack Compose**

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0-blue.svg)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Jetpack%20Compose-Latest-brightgreen.svg)](https://developer.android.com/jetpack/compose)

---

## ✨ Introduction

**Montage Android** is a **Jetpack Compose-based design system library** developed by Wanted.
It is designed to ensure consistent UI/UX and boost productivity, providing components used in real production environments.

> Wanted's Mission —
> *A world where everyone can work as their true selves*
>
> Montage is the design system that technically implements this vision.

---

## 🚀 Features

* 🎯 **Production Ready** — Components verified in real services
* ⚡ **Jetpack Compose Based** — Implemented entirely with Compose, no XML
* 🎨 **Flexible Theme System** — Brand-customizable
* 📱 **Modern UI Patterns** — Based on Material Design 3
* 🧩 **Extensible Architecture** — Optimized for design system expansion
* 🌙 **Dark Mode Support**
* 🧪 **Testable Structure**

---

## 📋 Requirements

* Android API Level 26+
* Kotlin 2.0+
* Jetpack Compose

---

## 📦 Installation

Montage is distributed via **Maven Central**.

### 1️⃣ Add Maven Central Repository

`settings.gradle.kts`

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
```

### 2️⃣ Add Dependency

`build.gradle.kts`

```kotlin
dependencies {
    implementation("run.thespot.montage:montage-kmp:0.0.5")
}
```

> 💡 The release version in `library/publish.properties` must match the Git tag.

---

## 🧩 Usage

```kotlin
import androidx.compose.runtime.Composable
import com.wanted.android.designsystem.*

@Composable
fun ExampleScreen() {
    DesignSystemTheme {
        WantedButton(
            text = "Montage Design System",
            onClick = {}
        )
    }
}
```

---

## 🛠️ Development

```bash
# Clone repository
git clone https://github.com/run-spot/montage-kmp.git
cd montage-kmp

# Build
./gradlew :library:build

# Test
./gradlew :library:test

# Build AAR
./gradlew :library:buildAar

# Publish to Maven Local (for testing)
./gradlew :library:publishToMavenLocal
```

---

## 🏷️ Versioning

Montage uses **Git tag-based version management**.

* `MAJOR.MINOR.PATCH` (Semantic Versioning)
* Example: `3.1.0`

### Examples

| Change Type     | Version Bump |
| --------------- | ------------ |
| Breaking change | MAJOR        |
| New feature     | MINOR        |
| Bug fix         | PATCH        |

---

## 📚 Documentation

* Official Documentation: [https://montage.wanted.co.kr/](https://montage.wanted.co.kr/)

---

## 🤝 Contributing

This project is an open-source project managed by Wanted.

* External Pull Requests are currently not accepted.
* Bug reports and improvement suggestions can be submitted via GitHub Issues.

---

## 📄 License

This project is distributed under the **MIT License**.

For more details, see [LICENSE](LICENSE).

---

## 🙏 Open Source Usage

Montage is based on the following open-source projects:

* Jetpack Compose (Apache 2.0)
* Material Components (Apache 2.0)
* Lottie (Apache 2.0)
* Glide (BSD / MIT / Apache 2.0)

For more details, see [THIRD_PARTY_LICENSES.md](THIRD_PARTY_LICENSES.md).

---

**Made with ❤️ by Wanted**
