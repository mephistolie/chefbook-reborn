package io.chefbook.features.recipebook.dashboard.ui.components.blocks

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.chefbook.features.recipebook.dashboard.ui.components.elements.LatestRecipeCard
import io.chefbook.features.recipebook.dashboard.ui.components.elements.LatestRecipeCardSkeleton
import io.chefbook.core.android.compose.providers.theme.LocalTheme
import io.chefbook.features.recipebook.dashboard.R
import io.chefbook.sdk.recipe.book.api.external.domain.entities.LatestRecipeInfo

private const val KEY_PREFIX = "quick_access_card"

@OptIn(ExperimentalFoundationApi::class)
internal fun LazyGridScope.quickAccessBlock(
  recipes: List<LatestRecipeInfo>?,
  onRecipeClicked: (String) -> Unit,
) {
  if (recipes == null || recipes.isNotEmpty()) {
    item(
      span = { GridItemSpan(4) }
    ) {
      Text(
        text = stringResource(id = R.string.common_dashboard_screen_quick_access),
        style = LocalTheme.typography.h3,
        color = LocalTheme.colors.foregroundPrimary,
        modifier = Modifier.padding(12.dp, 28.dp, 12.dp, 12.dp),
      )
    }
    item(
      span = { GridItemSpan(4) }
    ) {
      LazyRow {
        item {
          Spacer(modifier = Modifier.width(12.dp))
        }
        recipes?.let {
          items(
            items = recipes,
            key = { recipe -> "${KEY_PREFIX}_${recipe.id}" }
          ) { recipe ->
            LatestRecipeCard(
              recipe = recipe,
              onRecipeClicked = onRecipeClicked,
              modifier = Modifier.animateItemPlacement(),
            )
          }
        } ?: items(2) {
          LatestRecipeCardSkeleton()
        }
      }
    }
  }
}
