package io.chefbook.sdk.encryption.recipe.impl.data.crypto

import io.chefbook.libs.encryption.SymmetricKey
import io.chefbook.sdk.recipe.core.api.external.domain.entities.DecryptedRecipeInfo
import io.chefbook.sdk.recipe.core.api.external.domain.entities.EncryptedRecipe
import io.chefbook.sdk.recipe.core.api.external.domain.entities.EncryptedRecipeInfo
import io.chefbook.sdk.encryption.recipe.api.internal.data.crypto.RecipeCryptor
import io.chefbook.sdk.recipe.core.api.external.domain.entities.DecryptedRecipe
import io.chefbook.sdk.recipe.crud.api.internal.data.models.DecryptedRecipeInput
import io.chefbook.sdk.recipe.crud.api.internal.data.models.EncryptedRecipeInput

internal actual object RecipeCryptorImpl : RecipeCryptor {

  actual override fun encryptRecipeInfo(recipe: DecryptedRecipeInfo, key: SymmetricKey): EncryptedRecipeInfo = TODO()

  actual override fun decryptRecipeInfo(recipe: EncryptedRecipeInfo, key: SymmetricKey): DecryptedRecipeInfo = TODO()

  actual override fun decryptRecipe(recipe: EncryptedRecipe, key: SymmetricKey): DecryptedRecipe = TODO()

  actual override fun encryptRecipeInput(recipe: DecryptedRecipeInput, key: SymmetricKey): EncryptedRecipeInput = TODO()
}
