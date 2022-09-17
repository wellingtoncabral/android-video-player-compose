package com.wcabral.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wcabral.core.designsystem.theme.DesignSystemTheme

@Composable
fun DesignSystemCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.surface,
    elevation: Dp = 0.dp,
    border: BorderStroke? = null,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        backgroundColor = backgroundColor,
        elevation = elevation,
        modifier = modifier.clickable { onClick() },
        border = border,
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    DesignSystemTheme {
        DesignSystemCard(
            modifier = Modifier
                .padding(10.dp)
                .size(200.dp)
        ) {}
    }
}