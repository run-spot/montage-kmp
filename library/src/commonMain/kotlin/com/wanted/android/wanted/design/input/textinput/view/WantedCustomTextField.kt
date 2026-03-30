package com.wanted.android.wanted.design.input.textinput.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.input.textinput.textfield.WantedTextFieldDefaults.RightVariant
import com.wanted.android.wanted.design.resources.Res
import com.wanted.android.wanted.design.resources.icon_normal_circle_check_fill
import com.wanted.android.wanted.design.resources.icon_normal_circle_close_fill
import com.wanted.android.wanted.design.resources.icon_normal_circle_exclamation_fill
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.OPACITY_43
import com.wanted.android.wanted.design.util.clickOnce
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun WantedCustomTextField(
    value: TextFieldValue,
    placeholder: String,
    error: Boolean,
    enabled: Boolean,
    rightButtonEnabled: Boolean,
    complete: Boolean,
    maxLines: Int,
    minLines: Int,
    maxWordCount: Int,
    enabledOverflowText: Boolean,
    interactionSource: MutableInteractionSource,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    rightButtonVariant: RightVariant,
    modifier: Modifier = Modifier,
    focused: State<Boolean> = interactionSource.collectIsFocusedAsState(),
    cursorBrush: Brush = SolidColor(DesignSystemTheme.colors.primaryNormal),
    background: Color = DesignSystemTheme.colors.backgroundNormalNormal,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    rightButton: String? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onClickRightButton: () -> Unit = {},
    onValueChange: (TextFieldValue) -> Unit = {},
    focusRequester: FocusRequester = FocusRequester()
) {
    val borderWidth = if (focused.value) 2.dp else 1.dp
    val outerBorderColor = when {
        !enabled -> DesignSystemTheme.colors.lineNormalAlternative
        error -> DesignSystemTheme.colors.statusNegative.copy(OPACITY_43)
        focused.value -> DesignSystemTheme.colors.primaryNormal.copy(OPACITY_43)
        else -> DesignSystemTheme.colors.lineNormalNeutral
    }

    WantedCustomTextFieldLayout(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(if (enabled) background else DesignSystemTheme.colors.fillAlternative)
            .border(borderWidth, outerBorderColor, RoundedCornerShape(12.dp))
            .height(IntrinsicSize.Min),
        textField = {
            BasicTextField(
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .padding(horizontal = 12.dp, vertical = 12.dp)
                    .height(DesignSystemTheme.typography.body1Regular.lineHeight.value.dp)
                    .fillMaxSize(),
                value = value,
                maxLines = maxLines,
                minLines = minLines,
                enabled = enabled,
                singleLine = true,
                cursorBrush = cursorBrush,
                interactionSource = interactionSource,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                visualTransformation = visualTransformation,
                textStyle = DesignSystemTheme.typography.body1Regular.copy(
                    color = if (enabled) DesignSystemTheme.colors.labelNormal else DesignSystemTheme.colors.labelAlternative
                ),
                onValueChange = {
                    when {
                        enabledOverflowText -> onValueChange(it)
                        it.text.length <= maxWordCount -> onValueChange(it)
                        it.text.length < value.text.length -> onValueChange(it)
                        else -> onValueChange(value)
                    }
                },
                decorationBox = { innerTextField ->
                    DecorationBox(
                        innerTextField = innerTextField,
                        placeholder = if (value.text.isEmpty() && placeholder.isNotEmpty()) {
                            {
                                Text(
                                    text = placeholder,
                                    style = DesignSystemTheme.typography.body1Regular,
                                    color = if (enabled) {
                                        DesignSystemTheme.colors.labelAssistive
                                    } else {
                                        DesignSystemTheme.colors.labelDisable
                                    }
                                )
                            }
                        } else {
                            null
                        },
                        leadingIcon = leadingIcon,
                        trailingIcon = when {
                            !focused.value && enabled && error -> {
                                {
                                    Icon(
                                        modifier = Modifier.fillMaxSize(),
                                        painter = painterResource(Res.drawable.icon_normal_circle_exclamation_fill),
                                        tint = DesignSystemTheme.colors.statusNegative,
                                        contentDescription = ""
                                    )
                                }
                            }

                            !focused.value && complete -> {
                                {
                                    Icon(
                                        modifier = Modifier.fillMaxSize(),
                                        painter = painterResource(Res.drawable.icon_normal_circle_check_fill),
                                        tint = DesignSystemTheme.colors.primaryNormal,
                                        contentDescription = ""
                                    )
                                }
                            }

                            trailingIcon == null && value.text.isNotEmpty() && enabled && focused.value -> {
                                {
                                    Icon(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(CircleShape)
                                            .clickOnce { onValueChange(value.copy("")) },
                                        painter = painterResource(Res.drawable.icon_normal_circle_close_fill),
                                        tint = DesignSystemTheme.colors.labelAssistive,
                                        contentDescription = ""
                                    )
                                }
                            }

                            else -> trailingIcon
                        },
                        trailingContent = trailingContent
                    )
                }
            )
        },
        rightButton = if (rightButton.isNullOrEmpty()) {
            null
        } else {
            {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .clickOnce(isEnableRightButton(rightButtonEnabled, enabled)) { onClickRightButton() },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    WantedTextFieldButton(
                        modifier = Modifier
                            .background(if (rightButtonEnabled) DesignSystemTheme.colors.transparent else DesignSystemTheme.colors.fillAlternative)
                            .borderExceptStart(
                                topEndRadius = 12.dp.value,
                                bottomEndRadius = 12.dp.value,
                                color = outerBorderColor,
                                width = borderWidth.value
                            ),
                        title = rightButton,
                        rightButtonVariant = rightButtonVariant,
                        enable = isEnableRightButton(rightButtonEnabled, enabled)
                    )
                }
            }
        }
    )
}

@Composable
private fun isEnableRightButton(rightButtonEnabled: Boolean, enabled: Boolean): Boolean = rightButtonEnabled && enabled

@Composable
private fun DecorationBox(
    innerTextField: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {
        leadingIcon?.let {
            Box(
                modifier = Modifier.size(24.dp).padding(1.dp),
                contentAlignment = Alignment.Center
            ) { leadingIcon() }
        }

        Box(
            modifier = Modifier.weight(1f).padding(horizontal = 4.dp).wrapContentHeight(),
            contentAlignment = Alignment.CenterStart
        ) {
            placeholder?.let { placeholder() }
            innerTextField()
        }

        trailingIcon?.let {
            Box(
                modifier = Modifier.size(24.dp).padding(1.dp),
                contentAlignment = Alignment.Center
            ) { trailingIcon() }
        }

        trailingContent?.let {
            Box(modifier = Modifier.defaultMinSize(24.dp), contentAlignment = Alignment.Center) {
                trailingContent()
            }
        }
    }
}

@Composable
private fun WantedTextFieldButton(
    rightButtonVariant: RightVariant,
    title: String,
    enable: Boolean,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        text = title,
        textAlign = TextAlign.Center,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = DesignSystemTheme.typography.body1Bold,
        color = when {
            !enable -> DesignSystemTheme.colors.labelAssistive
            rightButtonVariant == RightVariant.Assistive -> DesignSystemTheme.colors.labelNormal
            else -> DesignSystemTheme.colors.primaryNormal
        }
    )
}

@Composable
private fun WantedCustomTextFieldLayout(
    textField: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    rightButton: @Composable (() -> Unit)? = null
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.weight(1f)) {
            textField()
        }
        rightButton?.let { rightButton() }
    }
}

private fun Modifier.borderExceptStart(
    topEndRadius: Float,
    bottomEndRadius: Float,
    color: Color,
    width: Float
): Modifier = drawBehind {
    val halfStroke = width / 2
    val path = Path().apply {
        moveTo(0f, halfStroke)
        lineTo(size.width - topEndRadius, halfStroke)
        arcTo(
            rect = Rect(
                left = size.width - topEndRadius * 2,
                top = halfStroke,
                right = size.width - halfStroke,
                bottom = topEndRadius * 2 + halfStroke
            ),
            startAngleDegrees = 270f,
            sweepAngleDegrees = 90f,
            forceMoveTo = false
        )
        lineTo(size.width - halfStroke, size.height - bottomEndRadius)
        arcTo(
            rect = Rect(
                left = size.width - bottomEndRadius * 2,
                top = size.height - bottomEndRadius * 2 - halfStroke,
                right = size.width - halfStroke,
                bottom = size.height - halfStroke
            ),
            startAngleDegrees = 0f,
            sweepAngleDegrees = 90f,
            forceMoveTo = false
        )
        lineTo(0f, size.height - halfStroke)
    }

    drawPath(path = path, color = color, style = Stroke(width = width))
}
