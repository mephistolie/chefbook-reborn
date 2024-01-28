package io.chefbook.sdk.profile.impl.data.sources.local.datastore.dto

import io.chefbook.sdk.profile.api.external.domain.entities.Profile
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileSerializable(
  @SerialName("id")
  val id: String = Profile.LOCAL_PROFILE_ID,
  @SerialName("email")
  val email: String? = null,
  @SerialName("username")
  val username: String? = null,
  @SerialName("creationTimestamp")
  val creationTimestamp: String? = null,
  @SerialName("avatar")
  val avatar: String? = null,
  @SerialName("premium")
  val premium: Boolean = false,
  @SerialName("broccoins")
  val broccoins: Int = 0,
  @SerialName("isOnline")
  val isOnline: Boolean = false,
)

fun ProfileSerializable.toEntity() =
  Profile(
    id = id,
    email = email,
    username = username,
    creationTimestamp = creationTimestamp,
    avatar = avatar,
    premium = premium,
    broccoins = broccoins,
    isOnline = isOnline
  )

fun Profile.toSerializable() =
  ProfileSerializable(
    id = id,
    email = email,
    username = username,
    creationTimestamp = creationTimestamp,
    avatar = avatar,
    premium = premium,
    broccoins = broccoins,
    isOnline = isOnline
  )
