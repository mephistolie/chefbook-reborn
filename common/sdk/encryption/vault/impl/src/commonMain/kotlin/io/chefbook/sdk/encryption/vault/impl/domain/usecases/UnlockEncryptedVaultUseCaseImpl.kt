package io.chefbook.sdk.encryption.vault.impl.domain.usecases

import io.chefbook.libs.encryption.AES_SALT_SIZE
import io.chefbook.libs.exceptions.NotFoundException
import io.chefbook.libs.utils.hash.sha1
import io.chefbook.libs.utils.result.EmptyResult
import io.chefbook.sdk.encryption.vault.api.external.domain.usecases.UnlockEncryptedVaultUseCase
import io.chefbook.sdk.encryption.vault.api.internal.data.repositories.EncryptedVaultRepository
import io.chefbook.sdk.profile.api.external.domain.entities.Profile
import io.chefbook.sdk.profile.api.internal.data.repositories.ProfileRepository

internal class UnlockEncryptedVaultUseCaseImpl(
  private val encryptionRepository: EncryptedVaultRepository,
  private val profileRepository: ProfileRepository,
) : UnlockEncryptedVaultUseCase {

  override suspend operator fun invoke(password: String): EmptyResult {
    val userId = profileRepository.getProfile().getOrNull()?.id ?: return Result.failure(NotFoundException())
    val salt = userId.sha1.encodeToByteArray().copyOf(AES_SALT_SIZE)
    return encryptionRepository.unlockEncryptedVault(password, salt)
  }
}
