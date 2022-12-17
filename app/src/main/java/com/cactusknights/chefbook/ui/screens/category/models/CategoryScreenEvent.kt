package com.cactusknights.chefbook.ui.screens.category.models

import com.mysty.chefbook.api.category.domain.entities.CategoryInput

sealed class CategoryScreenEvent {
    data class LoadRecipesInCategory(val categoryId: String) : CategoryScreenEvent()
    data class OpenRecipeScreen(val recipeId: String) : CategoryScreenEvent()
    data class EditCategory(val input: CategoryInput) : CategoryScreenEvent()
    data class SaveEditCategoryDialogState(val input: CategoryInput?) : CategoryScreenEvent()
    object DeleteCategory : CategoryScreenEvent()
    object Back : CategoryScreenEvent()

    sealed class ChangeDialogState(open val isVisible: Boolean) : CategoryScreenEvent() {
        data class Edit(override val isVisible: Boolean) : ChangeDialogState(isVisible)
        data class Delete(override val isVisible: Boolean) : ChangeDialogState(isVisible)
    }

}
