@file:JvmName("WantedRoundCheckBoxViewKt")

package com.wanted.android.wanted.design.input.input.control

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.checkbox.MaterialCheckBox
import kotlin.jvm.JvmName

@Deprecated("Deprecated")
class WantedRoundCheckBox : MaterialCheckBox {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )
}
