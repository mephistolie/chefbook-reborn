package io.chefbook.sdk.recipe.community.impl.domain

import io.chefbook.libs.models.language.Language
import io.chefbook.libs.utils.language.getSystemLanguageCode
import io.chefbook.sdk.recipe.community.api.external.domain.entities.RecipesFilter
import io.chefbook.sdk.recipe.community.api.external.domain.entities.RecipesSorting
import io.chefbook.sdk.recipe.community.api.external.domain.usecases.GetTopRecipesUseCase
import io.chefbook.sdk.recipe.community.impl.data.models.RecipesQuery
import io.chefbook.sdk.recipe.community.impl.data.repositories.CommunityRecipesRepository
import io.chefbook.sdk.recipe.core.api.external.domain.entities.RecipeInfo
import io.chefbook.sdk.settings.api.internal.data.repositories.SettingsRepository

internal class GetTopRecipesUseCaseImpl(
  private val recipesRepository: CommunityRecipesRepository,
  private val settingsRepository: SettingsRepository,
) : GetTopRecipesUseCase {

  override suspend fun invoke(
    lastRecipe: RecipeInfo?,
  ) = recipesRepository.getRecipes(
    RecipesQuery(
      recipesCount = RecipesFilter.DEFAULT_RECIPES_COUNT,

      sorting = RecipesSorting.RATING,
      lastRecipeId = lastRecipe?.id,
      lastRating = lastRecipe?.rating?.index,

      recipeLanguages = settingsRepository.getCommunityRecipesLanguages().map(Language::code),
      userLanguage = getSystemLanguageCode(),
    )
  )
}
