package com.cactusknights.chefbook.ui.screens.auth.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.cactusknights.chefbook.R
import com.mysty.chefbook.core.ui.compose.providers.theme.LocalTheme

@Composable
fun ChefBookLogo(
    modifier: Modifier = Modifier,
) {
    val colors = LocalTheme.colors
    val typography = LocalTheme.typography

    Row(
        modifier = modifier.wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_broccy),
            contentDescription = "logo",
            modifier = Modifier.size(48.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = "Chef",
            style = typography.h1,
            color = colors.tintPrimary
        )
        Text(
            text = "Book",
            style = typography.h1,
            color = colors.foregroundPrimary
        )
    }
}
