package com.wanted.android.wanted.design.input.select

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wanted.android.wanted.design.input.select.view.WantedMultiSelectContents
import com.wanted.android.wanted.design.input.select.view.WantedSelectContentLayout
import com.wanted.android.wanted.design.input.select.view.WantedSelectLayout
import com.wanted.android.wanted.design.input.select.view.WantedSelectPlaceHolder
import com.wanted.android.wanted.design.input.textinput.view.ComponentTitle
import com.wanted.android.wanted.design.presentation.modal.WantedModalContract
import com.wanted.android.wanted.design.resources.Res
import com.wanted.android.wanted.design.resources.icon_normal_chevron_down_thick_small
import com.wanted.android.wanted.design.resources.icon_normal_chevron_up_thick_small
import com.wanted.android.wanted.design.resources.icon_normal_circle_exclamation_fill
import com.wanted.android.wanted.design.theme.DesignSystemTheme
import com.wanted.android.wanted.design.util.DevicePreviews
import com.wanted.android.wanted.design.util.OPACITY_43
import com.wanted.android.wanted.design.util.clickOnce
import com.wanted.android.wanted.design.util.wantedRippleEffect
import org.jetbrains.compose.resources.painterResource

@Composable
fun WantedSelect(
    value: String,
    modifier: Modifier = Modifier,
    title: String? = null,
    description: String? = null,
    placeHolder: String = "",
    confirmText: String = "",
    isRequiredBadge: Boolean = false,
    negative: Boolean = false,
    focused: Boolean = false,
    enabled: Boolean = true,
    selectValueList: List<String> = emptyList(),
    selectedValue: String? = null,
    bottomSheetType: WantedModalContract.ModalType = WantedModalContract.ModalType.Flexible,
    selectType: WantedSelectDefaults.SelectType = WantedSelectDefaults.SelectType.CheckMark,
    background: Color = DesignSystemTheme.colors.backgroundTransparentAlternative,
    onClick: () -> Unit = {},
    onSelect: (item: String) -> Unit = {},
    leadingIcon: @Composable (() -> Unit)? = null
) {
    WantedSelect(
        modifier = modifier,
        title = title,
        description = description,
        confirmText = confirmText,
        selectData = WantedSelectData(text = value),
        placeHolder = placeHolder,
        isRequiredBadge = isRequiredBadge,
        negative = negative,
        focused = focused,
        enabled = enabled,
        bottomSheetType = bottomSheetType,
        selectDataList = selectValueList.map { WantedSelectData(text = it) },
        selectedData = selectedValue?.let { WantedSelectData(text = it) },
        selectType = selectType,
        background = background,
        onClick = onClick,
        leadingIcon = leadingIcon,
        onSelectData = { item -> onSelect(item.text) }
    )
}

@Composable
fun WantedSelect(
    selectData: WantedSelectData?,
    modifier: Modifier = Modifier,
    title: String? = null,
    description: String? = null,
    confirmText: String = "",
    placeHolder: String = "",
    isRequiredBadge: Boolean = false,
    negative: Boolean = false,
    focused: Boolean = false,
    enabled: Boolean = true,
    selectDataList: List<WantedSelectData> = emptyList(),
    selectedData: WantedSelectData? = null,
    bottomSheetType: WantedModalContract.ModalType = WantedModalContract.ModalType.Flexible,
    selectType: WantedSelectDefaults.SelectType = WantedSelectDefaults.SelectType.CheckMark,
    background: Color = DesignSystemTheme.colors.backgroundTransparentAlternative,
    onClick: () -> Unit = {},
    onSelectData: (item: WantedSelectData) -> Unit = {},
    leadingIcon: @Composable (() -> Unit)? = null
) {
    val isShowBottomSheetDialog = remember { mutableStateOf(false) }
    val isFocus = remember(focused) { mutableStateOf(focused) }

    WantedSelectImpl(
        modifier = modifier,
        title = title,
        description = description,
        isRequiredBadge = isRequiredBadge,
        negative = negative,
        focused = isFocus.value,
        enabled = enabled,
        background = background,
        onClick = {
            isFocus.value = true
            onClick()
            if (selectDataList.isNotEmpty()) {
                isShowBottomSheetDialog.value = true
            }
        },
        leadingIcon = leadingIcon,
        contents = {
            if (selectData?.text.isNullOrEmpty()) {
                WantedSelectPlaceHolder(
                    modifier = Modifier.fillMaxWidth(),
                    placeHolder = placeHolder,
                    enabled = enabled
                )
            } else {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    text = selectData.text,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = DesignSystemTheme.typography.body1Regular,
                    color = if (enabled) {
                        DesignSystemTheme.colors.labelNormal
                    } else {
                        DesignSystemTheme.colors.labelAlternative
                    }
                )
            }
        }
    )

    WantedSelectBottomSheetHost(
        modifier = Modifier,
        isShow = selectDataList.isNotEmpty() && isShowBottomSheetDialog.value,
        items = selectDataList,
        confirmText = confirmText,
        selectType = selectType,
        bottomSheetType = bottomSheetType,
        selectedItem = selectedData,
        onSelect = { item ->
            isFocus.value = false
            onSelectData(item)
            isShowBottomSheetDialog.value = false
        },
        onDismissRequest = {
            isFocus.value = false
            isShowBottomSheetDialog.value = false
        }
    )
}

@Composable
fun WantedSelect(
    selectedDataList: List<WantedSelectData>,
    modifier: Modifier = Modifier,
    title: String? = null,
    description: String? = null,
    confirmText: String = "",
    placeHolder: String = "",
    isRequiredBadge: Boolean = false,
    negativeDataList: List<WantedSelectData> = emptyList(),
    focused: Boolean = false,
    enabled: Boolean = true,
    overflow: Boolean = false,
    selectDataList: List<WantedSelectData> = emptyList(),
    selectType: WantedSelectDefaults.SelectType = WantedSelectDefaults.SelectType.CheckBox,
    render: WantedSelectDefaults.MultiSelectRender = WantedSelectDefaults.MultiSelectRender.Text,
    background: Color = DesignSystemTheme.colors.backgroundTransparentAlternative,
    onDeleteData: (WantedSelectData) -> Unit = {},
    onClick: () -> Unit = {},
    onSelectDataList: (itemList: List<WantedSelectData>) -> Unit = {},
    leadingIcon: @Composable (() -> Unit)? = null
) {
    val isShowBottomSheetDialog = remember { mutableStateOf(false) }
    val isFocus = remember(focused) { mutableStateOf(focused) }

    CompositionLocalProvider(LocalWantedSelectBackground.provides(background)) {
        WantedSelectImpl(
            modifier = modifier,
            title = title,
            description = description,
            isRequiredBadge = isRequiredBadge,
            negative = negativeDataList.isNotEmpty(),
            focused = isFocus.value,
            enabled = enabled,
            background = background,
            onClick = {
                isFocus.value = true
                onClick()
                if (selectDataList.isNotEmpty()) {
                    isShowBottomSheetDialog.value = true
                }
            },
            leadingIcon = leadingIcon,
            contents = {
                WantedMultiSelectContents(
                    modifier = Modifier.fillMaxWidth(),
                    valueList = selectedDataList,
                    placeHolder = placeHolder,
                    errorList = negativeDataList,
                    overflow = overflow,
                    enabled = enabled,
                    render = render,
                    onDelete = onDeleteData
                )
            }
        )
    }

    WantedMultiSelectBottomSheetHost(
        modifier = Modifier,
        isShow = selectDataList.isNotEmpty() && isShowBottomSheetDialog.value,
        items = selectDataList,
        confirmText = confirmText,
        selectType = selectType,
        dialogType = WantedModalContract.ModalType.Flexible,
        selectedItemList = selectedDataList,
        onSelect = { itemList ->
            isFocus.value = false
            isShowBottomSheetDialog.value = false
            onSelectDataList(itemList)
        },
        onDismissRequest = {
            isShowBottomSheetDialog.value = false
            isFocus.value = false
        }
    )
}

@Composable
fun WantedSelectWithString(
    selectedValueList: List<String>,
    modifier: Modifier = Modifier,
    title: String? = null,
    description: String? = null,
    confirmText: String = "",
    placeHolder: String = "",
    isRequiredBadge: Boolean = false,
    negativeList: List<String> = emptyList(),
    focused: Boolean = false,
    enabled: Boolean = true,
    overflow: Boolean = false,
    selectValueList: List<String> = emptyList(),
    selectType: WantedSelectDefaults.SelectType = WantedSelectDefaults.SelectType.CheckBox,
    render: WantedSelectDefaults.MultiSelectRender = WantedSelectDefaults.MultiSelectRender.Text,
    background: Color = DesignSystemTheme.colors.backgroundTransparentAlternative,
    leadingIcon: (@Composable (() -> Unit))? = null,
    onDelete: (String) -> Unit = {},
    onClick: () -> Unit = {},
    onSelectList: (itemList: List<String>) -> Unit = {}
) {
    WantedSelect(
        modifier = modifier,
        title = title,
        description = description,
        confirmText = confirmText,
        selectedDataList = selectedValueList.map { WantedSelectData(text = it) },
        placeHolder = placeHolder,
        isRequiredBadge = isRequiredBadge,
        negativeDataList = negativeList.map { WantedSelectData(text = it) },
        focused = focused,
        enabled = enabled,
        overflow = overflow,
        selectDataList = selectValueList.map { WantedSelectData(text = it) },
        selectType = selectType,
        render = render,
        background = background,
        onDeleteData = { onDelete(it.text) },
        onClick = onClick,
        leadingIcon = leadingIcon,
        onSelectDataList = { itemList -> onSelectList(itemList.map { it.text }) }
    )
}

@Composable
private fun WantedSelectImpl(
    background: Color,
    modifier: Modifier = Modifier,
    title: String? = null,
    description: String? = null,
    isRequiredBadge: Boolean = false,
    negative: Boolean = false,
    focused: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    leadingIcon: @Composable (() -> Unit)? = null,
    contents: @Composable () -> Unit
) {
    WantedSelectLayout(
        modifier = modifier,
        title = title?.let {
            {
                ComponentTitle(
                    modifier = Modifier.fillMaxWidth(),
                    title = title,
                    isRequiredBadge = isRequiredBadge
                )
            }
        },
        select = {
            WantedSelectContentLayout(
                modifier = Modifier
                    .border(
                        shape = RoundedCornerShape(12.dp),
                        color = when {
                            negative || focused -> DesignSystemTheme.colors.backgroundNormalNormal.copy(alpha = OPACITY_43)
                            else -> DesignSystemTheme.colors.transparent
                        },
                        width = if (focused) 2.dp else 1.dp
                    )
                    .border(
                        shape = RoundedCornerShape(12.dp),
                        color = when {
                            !enabled -> DesignSystemTheme.colors.lineNormalAlternative
                            negative -> DesignSystemTheme.colors.statusNegative.copy(OPACITY_43)
                            focused -> DesignSystemTheme.colors.primaryNormal.copy(OPACITY_43)
                            else -> DesignSystemTheme.colors.lineNormalNeutral
                        },
                        width = if (focused) 2.dp else 1.dp
                    )
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (enabled) background else DesignSystemTheme.colors.fillAlternative)
                    .clickOnce(
                        enabled = enabled,
                        interactionSource = remember { MutableInteractionSource() },
                        indication = getSelectRippleEffect(
                            enabled = enabled,
                            focused = focused,
                            negative = negative
                        )
                    ) {
                        onClick()
                    }
                    .padding(12.dp),
                leadingIcon = leadingIcon,
                contents = { contents() },
                rightButton = {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(
                            if (focused) {
                                Res.drawable.icon_normal_chevron_up_thick_small
                            } else {
                                Res.drawable.icon_normal_chevron_down_thick_small
                            }
                        ),
                        tint = if (enabled) {
                            DesignSystemTheme.colors.labelAlternative
                        } else {
                            DesignSystemTheme.colors.labelDisable
                        },
                        contentDescription = ""
                    )
                },
                trailingIcon = if (negative && !focused && enabled) {
                    {
                        Icon(
                            modifier = Modifier
                                .size(24.dp)
                                .padding(1.dp),
                            painter = painterResource(Res.drawable.icon_normal_circle_exclamation_fill),
                            tint = DesignSystemTheme.colors.statusNegative,
                            contentDescription = ""
                        )
                    }
                } else {
                    null
                }
            )
        },
        description = description?.let {
            {
                Text(
                    text = description,
                    style = DesignSystemTheme.typography.caption1Regular,
                    color = when {
                        enabled && negative -> DesignSystemTheme.colors.statusNegative
                        else -> DesignSystemTheme.colors.labelAlternative
                    },
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    )
}

@Composable
private fun getSelectRippleEffect(
    negative: Boolean = false,
    focused: Boolean = false,
    enabled: Boolean = true,
) = when {
    !enabled -> null
    negative -> wantedRippleEffect(DesignSystemTheme.colors.statusNegative)
    focused -> wantedRippleEffect(DesignSystemTheme.colors.primaryNormal)
    else -> wantedRippleEffect(DesignSystemTheme.colorsOpacity.labelNormalOpacity12)
}

@Composable
internal expect fun WantedSelectBottomSheetHost(
    modifier: Modifier = Modifier,
    isShow: Boolean,
    items: List<WantedSelectData>,
    confirmText: String,
    selectType: WantedSelectDefaults.SelectType = WantedSelectDefaults.SelectType.CheckMark,
    bottomSheetType: WantedModalContract.ModalType = WantedModalContract.ModalType.Flexible,
    selectedItem: WantedSelectData? = null,
    onSelect: (item: WantedSelectData) -> Unit,
    onDismissRequest: () -> Unit
)

@Composable
internal expect fun WantedMultiSelectBottomSheetHost(
    modifier: Modifier = Modifier,
    isShow: Boolean,
    items: List<WantedSelectData>,
    confirmText: String,
    selectType: WantedSelectDefaults.SelectType = WantedSelectDefaults.SelectType.CheckBox,
    dialogType: WantedModalContract.ModalType = WantedModalContract.ModalType.Flexible,
    selectedItemList: List<WantedSelectData>,
    onSelect: (itemList: List<WantedSelectData>) -> Unit,
    onDismissRequest: () -> Unit
)

@DevicePreviews
@Composable
private fun WantedSelectPreview() {
    DesignSystemTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
            ) {
            }
        }
    }
}
