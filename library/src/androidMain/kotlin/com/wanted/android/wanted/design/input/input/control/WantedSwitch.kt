@file:JvmName("WantedSwitchViewKt")

package com.wanted.android.wanted.design.input.input.control

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlin.jvm.JvmName

class WantedSwitch : SwitchMaterial {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        scaleX = 0.7F
        scaleY = 0.7F
    }
}
