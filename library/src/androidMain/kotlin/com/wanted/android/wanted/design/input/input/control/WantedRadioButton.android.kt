@file:JvmName("WantedRadioButtonViewKt")

package com.wanted.android.wanted.design.input.input.control

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.radiobutton.MaterialRadioButton
import kotlin.jvm.JvmName

class WantedRadioButton : MaterialRadioButton {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )
}
