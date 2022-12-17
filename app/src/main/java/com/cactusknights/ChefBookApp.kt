package com.cactusknights

import android.app.Application
import com.cactusknights.chefbook.BuildConfig
import com.cactusknights.chefbook.di.viewModelModule
import com.mysty.chefbook.api.auth.data.di.apiAuthModule
import com.mysty.chefbook.api.category.data.di.apiCategoryModule
import com.mysty.chefbook.api.common.di.coreDataModule
import com.mysty.chefbook.api.common.di.databaseModule
import com.mysty.chefbook.api.common.di.networkModule
import com.mysty.chefbook.api.encryption.data.di.apiEncryptionModule
import com.mysty.chefbook.api.files.data.di.apiFilesModule
import com.mysty.chefbook.api.profile.data.di.apiProfileModule
import com.mysty.chefbook.api.recipe.data.di.apiRecipeModule
import com.mysty.chefbook.api.settings.data.di.apiSettingsModule
import com.mysty.chefbook.api.shoppinglist.data.di.apiShoppingListModule
import com.mysty.chefbook.api.sources.data.di.apiSourcesModule
import java.security.Security
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.spongycastle.jce.provider.BouncyCastleProvider
import timber.log.Timber

class ChefBookApp: Application() {

    override fun onCreate() {
        super.onCreate()
        configureBouncyCastle()
        configureTimber()
        configureKoin()
    }

    private fun configureBouncyCastle() {
        Security.insertProviderAt(BouncyCastleProvider(), 1)
    }

    private fun configureTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun configureKoin() {
        startKoin {
            androidContext(this@ChefBookApp)
            modules(
                coreDataModule,
                databaseModule,
                networkModule,

                apiSettingsModule,
                apiFilesModule,
                apiSourcesModule,
                apiAuthModule,
                apiProfileModule,
                apiEncryptionModule,
                apiRecipeModule,
                apiCategoryModule,
                apiShoppingListModule,

                viewModelModule,
            )
        }
    }
}
