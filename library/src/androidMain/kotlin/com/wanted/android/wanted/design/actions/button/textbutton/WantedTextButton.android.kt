package com.wanted.android.wanted.design.actions.button.textbutton

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.AbstractComposeView
import com.wanted.android.designsystem.R
import com.wanted.android.wanted.design.actions.button.config.WantedButtonDefaults
import com.wanted.android.wanted.design.actions.button.config.buttonDrawableSize
import com.wanted.android.wanted.design.actions.button.view.WantedButtonSideIcon
import com.wanted.android.wanted.design.util.ButtonSize
import com.wanted.android.wanted.design.util.ButtonType
import com.wanted.android.wanted.design.util.ButtonVariant
import com.wanted.android.wanted.design.util.getTextButtonSize

class WantedTextButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {

    lateinit var size: ButtonSize
    var text by mutableStateOf("")
    var buttonType by mutableStateOf(ButtonType.PRIMARY)
    var buttonStatus by mutableStateOf(true)
    var leftDrawable by mutableStateOf<Int?>(null)
    var rightDrawable by mutableStateOf<Int?>(null)
    var isClickOnce by mutableStateOf(true)
    private var buttonWidth: Int = -1
    private var buttonHeight: Int = -1
    private var onClickListener by mutableStateOf({})

    init {
        attrs?.let {
            context.obtainStyledAttributes(it, R.styleable.WantedButton).run {
                text = getString(R.styleable.WantedButton_text) ?: ""
                buttonType = ButtonType.entries[getInteger(R.styleable.WantedButton_button_type, 0)]
                size = ButtonSize.entries[getInteger(R.styleable.WantedButton_button_size, 0)]
                leftDrawable = getResourceId(R.styleable.WantedButton_leftDrawable, 0)
                rightDrawable = getResourceId(R.styleable.WantedButton_rightDrawable, 0)
                buttonStatus = getBoolean(R.styleable.WantedButton_enabled, true)
                isClickOnce = getBoolean(R.styleable.WantedButton_clickOnce, true)
                recycle()
            }

            context.obtainStyledAttributes(it, R.styleable.Layout).run {
                buttonWidth = getLayoutDimension(R.styleable.Layout_android_layout_width, -2)
                buttonHeight = getLayoutDimension(R.styleable.Layout_android_layout_height, -2)
                recycle()
            }
        }
    }

    override fun setEnabled(enabled: Boolean) {
        buttonStatus = enabled
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        onClickListener = { listener?.onClick(this) }
    }

    @Composable
    override fun Content() {
        val buttonDefault = WantedButtonDefaults.getDefault(
            variant = ButtonVariant.TEXT,
            type = buttonType,
            size = size,
            enabled = buttonStatus
        )
        val leadingDrawable = leftDrawable?.takeIf { it != 0 }
        val trailingDrawable = rightDrawable?.takeIf { it != 0 }
        WantedTextButtonWithSlots(
            text = text,
            modifier = Modifier.getTextButtonSize(
                buttonWidth = buttonWidth,
                buttonHeight = buttonHeight
            ),
            color = buttonType,
            size = size,
            enabled = buttonStatus,
            leadingContent = leadingDrawable?.let { drawableRes ->
                {
                    WantedButtonSideIcon(
                        modifier = Modifier
                            .buttonDrawableSize(ButtonVariant.TEXT, buttonDefault.size)
                            .alpha(1f),
                        drawableRes = drawableRes,
                        tint = buttonDefault.leftIconTintColor
                    )
                }
            },
            trailingContent = trailingDrawable?.let { drawableRes ->
                {
                    WantedButtonSideIcon(
                        modifier = Modifier
                            .buttonDrawableSize(ButtonVariant.TEXT, buttonDefault.size)
                            .alpha(1f),
                        drawableRes = drawableRes,
                        tint = buttonDefault.rightIconTintColor
                    )
                }
            },
            onClick = onClickListener,
            buttonDefault = buttonDefault
        )
    }
}
