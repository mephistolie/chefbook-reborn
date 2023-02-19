package com.mysty.chefbook.features.recipe.info.ui.components.cooking

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mysty.chefbook.api.recipe.domain.entities.cooking.CookingItem
import com.mysty.chefbook.core.android.compose.providers.theme.LocalTheme

@Composable
internal fun CookingStep(
  number: Int,
  step: CookingItem.Step,
  onPictureClicked: (String) -> Unit,
  modifier: Modifier = Modifier,
) {
  val colors = LocalTheme.colors
  val typography = LocalTheme.typography

  Row(
    verticalAlignment = Alignment.Top,
    horizontalArrangement = Arrangement.Start,
    modifier = modifier,
  ) {
    Text(
      text = "$number.",
      style = typography.body1,
      color = colors.tintPrimary,
      modifier = Modifier.width(26.dp)
    )
    Column {
      Text(
        text = step.description,
        style = typography.headline1,
        color = colors.foregroundPrimary,
      )
      step.pictures?.let { pictures ->
        CookingStepPictures(
          pictures = pictures,
          onPictureClicked = onPictureClicked
        )
        Spacer(modifier = Modifier.height(8.dp))
      }
    }
  }
}
