package com.cactusknights.chefbook.ui.screens.recipebook

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cactusknights.chefbook.ui.navigation.Destination
import com.cactusknights.chefbook.ui.screens.recipebook.models.RecipeBookScreenEffect
import com.cactusknights.chefbook.ui.screens.recipebook.views.RecipeBookScreenDisplay

@Composable
fun RecipeBookScreen(
    navController: NavHostController,
    topBarScale: Float = 1F,
    viewModel: RecipeBookScreenViewModel = hiltViewModel(),
) {
    val recipeBookState = viewModel.state.collectAsState()

    RecipeBookScreenDisplay(
        state = recipeBookState.value,
        onEvent = viewModel::obtainEvent,
        topBarScale = topBarScale,
    )

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is RecipeBookScreenEffect.RecipeSearchOpened -> {
                    navController.navigate(Destination.Home.RecipeBook.Search.route)
                }
                is RecipeBookScreenEffect.FavouriteOpened -> {
                    navController.navigate(Destination.Home.RecipeBook.Favourite.route)
                }
                is RecipeBookScreenEffect.CategoryOpened -> {
                    navController.navigate(Destination.Home.RecipeBook.Category.route(effect.categoryId))
                }
                is RecipeBookScreenEffect.CommunityRecipesOpened -> {

                }
                is RecipeBookScreenEffect.EncryptionMenuOpened -> {
                    navController.navigate(Destination.Encryption.route())
                }
                is RecipeBookScreenEffect.RecipeCreationScreenOpened -> {
                    navController.navigate(Destination.RecipeInput.route())
                }
                is RecipeBookScreenEffect.RecipeOpened -> {
                    navController.navigate(Destination.Recipe.route(effect.recipeId))
                }
            }
        }
    }
}
