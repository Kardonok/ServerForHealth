package com.sfh.models

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*


object Profiles: Table() {
    //val userId = integer("user_id").uniqueIndex()
    private val userName = Profiles.varchar("user_name", 30)
    private val userPassword = Profiles.varchar("user_password", 30)
    private val userHeight = Profiles.varchar("user_height",5)
    private val userWeight = Profiles.varchar("user_weight",5)
    private val userGender = Profiles.varchar("user_gender", 5)
    private val userToken = Profiles.varchar("user_token", 50)

    fun insertProfile(profileDTO: ProfileDTO)
    {
        transaction {
            Profiles.insert {
                it[userName] = profileDTO.userName
                it[userPassword] = profileDTO.userPassword
                it[userHeight] = profileDTO.userHeight
                it[userWeight] = profileDTO.userWeight
                it[userGender] = profileDTO.userGender
                it[userToken] = profileDTO.userToken
            }
        }
    }

    fun getProfile(name: String): ProfileDTO? {
        val profile = transaction {
            Profiles.selectAll()
                .firstOrNull { it[userName] == name }
        }

        if (profile != null) {
            return ProfileDTO(
                //userId = profile[userId],
                userName = profile[userName],
                userPassword = profile[userPassword],
                userHeight = profile[userHeight],
                userWeight = profile[userWeight],
                userGender = profile[userGender],
                userToken = profile[userToken],
            )
        }
        return null
    }
}