package com.wcabral.core.designsystem.component

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
import androidx.compose.ui.unit.dp
import com.wcabral.core.designsystem.theme.DesignSystemTheme

@Composable
fun DesignSystemCard(
    backgroundColor: Color = MaterialTheme.colors.onBackground.copy(alpha = 0.05f),
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        backgroundColor = backgroundColor,
        elevation = 0.dp,
        modifier = modifier.clickable { onClick() }
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