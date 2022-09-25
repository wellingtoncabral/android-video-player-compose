package com.wcabral.core.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wcabral.core.designsystem.dimen.DesignSystemDimens
import com.wcabral.core.designsystem.icon.DesignSystemIcons
import com.wcabral.core.designsystem.theme.DesignSystemColors

@Composable
fun DesignSystemRating(value: Double) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = DesignSystemDimens.Padding.ExtraSmall)
    ) {
        DesignSystemIcon(
            imageVector = DesignSystemIcons.Star,
            contentDescription = null,
            tint = DesignSystemColors.Star
        )
        Spacer(modifier = Modifier.padding(horizontal = DesignSystemDimens.Padding.ExtraSmall))
        Text(
            text = value.toString(),
            style = MaterialTheme.typography.button,
        )
    }
}

@Preview
@Composable
fun DesignSystemPreview() {
    DesignSystemRating(4.7)
}
