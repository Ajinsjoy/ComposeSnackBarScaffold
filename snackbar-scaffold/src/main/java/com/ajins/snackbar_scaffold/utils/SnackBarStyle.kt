package com.ajins.snackbar_scaffold.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

data class SnackBarStyle(
    val padding: PaddingValues = PaddingValues(16.dp),
    val actionColor: Color,
    val contentColor: Color,
    val containerColor: Color,
    val leadingIconColor: Color,
    val shape: Shape = RoundedCornerShape(4.dp),
    val actionContentColor: Color = Color.Blue,
    val dismissActionContentColor: Color,
    val actionOnNewLine: Boolean = false,
    val textStyle: TextStyle
)
