package com.mysty.chefbook.features.shoppinglist.control.di

import com.mysty.chefbook.features.shoppinglist.control.ui.screen.ShoppingListScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureShoppingListModule = module {
    viewModelOf(::ShoppingListScreenViewModel)
}
