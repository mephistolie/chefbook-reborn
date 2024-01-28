package io.chefbook.features.recipebook.search.ui.mvi

import io.chefbook.sdk.recipe.core.api.external.domain.entities.DecryptedRecipeInfo
import io.chefbook.libs.mvi.MviState

internal data class RecipeBookSearchScreenState(
  val query: String = "",
  val categories: List<io.chefbook.sdk.category.api.external.domain.entities.Category> = emptyList(),
  val recipes: List<DecryptedRecipeInfo> = emptyList(),
  val isLoading: Boolean = false,
) : MviState
