package com.mysty.chefbook.features.recipe.input.ui.screens.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mysty.chefbook.api.recipe.domain.entities.RecipeInput
import com.mysty.chefbook.core.android.compose.providers.theme.LocalTheme
import com.mysty.chefbook.design.components.buttons.Counter
import com.mysty.chefbook.features.recipe.input.R

@Composable
internal fun ServingsBlock(
  state: RecipeInput,
  onSetServings: (Int?) -> Unit,
  modifier: Modifier = Modifier,
) {
  val colors = LocalTheme.colors
  val typography = LocalTheme.typography

  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Text(
      text = stringResource(R.string.common_general_servings),
      style = typography.headline1,
      color = colors.foregroundPrimary,
    )
    Counter(
      count = state.servings ?: 0,
      isTextEditable = true,
      onValueChange = { value -> onSetServings(value.toIntOrNull()) },
      onMinusClicked = { onSetServings((state.servings ?: 0) - 1) },
      onPlusClicked = { onSetServings((state.servings ?: 0) + 1) },
    )
  }
}