package com.wcabral.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wcabral.core.designsystem.icon.DesignSystemIcons
import com.wcabral.core.designsystem.theme.DesignSystemTheme

@Composable
fun DesignSystemRoundedIconButton(
    icon: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    size: Dp = DesignSystemButtonDefaults.RoundedIconButton.Size,
    alpha: Float = DesignSystemButtonDefaults.RoundedIconButton.Alpha,
    background: Color = MaterialTheme.colorScheme.tertiary,
    iconColor: Color = MaterialTheme.colorScheme.onTertiary,
    iconSize: Dp = DesignSystemButtonDefaults.RoundedIconButton.IconSize,
) {
    Surface(
        shape = RoundedCornerShape(50.dp),
        modifier = modifier
            .size(size)
            .alpha(alpha)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .padding()
                .background(background),
            contentAlignment = Alignment.Center
        ){
            DesignSystemIcon(
                modifier = Modifier.size(iconSize),
                imageVector = icon,
                contentDescription = contentDescription,
                tint = iconColor,
            )
        }
    }
}

@Preview
@Composable
internal fun DesignSystemRoundButtonPreview() {
    DesignSystemTheme {
        DesignSystemRoundedIconButton(
            icon = DesignSystemIcons.PlayArrow,
            contentDescription = null,
            onClick = {  }
        )
    }
}