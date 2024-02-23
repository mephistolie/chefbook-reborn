package io.chefbook.features.community.recipes.ui.mvi

import io.chefbook.libs.mvi.MviSideEffect

sealed interface CommunityRecipesScreenEffect : MviSideEffect {

  data object Back : CommunityRecipesScreenEffect
  data object LanguagesPickerOpened : CommunityRecipesScreenEffect
  data object ProfileScreenOpened : CommunityRecipesScreenEffect

  data class FilterOpened(val focusSearch: Boolean = false) : CommunityRecipesScreenEffect
  data object FilterClosed : CommunityRecipesScreenEffect

  data class RecipeScreenOpened(val recipeId: String) : CommunityRecipesScreenEffect
  data object RecipeInputScreenOpened : CommunityRecipesScreenEffect
}
