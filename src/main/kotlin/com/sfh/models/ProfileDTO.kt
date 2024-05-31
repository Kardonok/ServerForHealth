package com.sfh.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
data class ProfileDTO(
    val userId: Int,
    val userName: String,
    val userPassword: String = "",
    val userHeight: String,
    val userWeight: String,
    val userGender: String = "",
    var userToken: String,
)

 */

@Serializable
data class ProfileDTO(
    //@SerialName(value = "user_id") val userId: Int,
    @SerialName(value = "user_name") val userName: String,
    @SerialName(value = "user_password") val userPassword: String,
    @SerialName(value = "user_height") val userHeight: String,
    @SerialName(value = "user_weight") val userWeight: String,
    @SerialName(value = "user_gender") val userGender: String,
    @SerialName(value = "user_token") var userToken: String = "",
)