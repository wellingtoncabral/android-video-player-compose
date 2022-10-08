package com.wcabral.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wcabral.core.designsystem.theme.DesignSystemTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DesignSystemCard(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    elevation: Dp = 0.dp,
    border: BorderStroke? = null,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors =  CardDefaults.cardColors(
            containerColor = containerColor
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = elevation
        ),
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