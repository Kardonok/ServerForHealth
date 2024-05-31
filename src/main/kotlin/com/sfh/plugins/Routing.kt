package com.sfh.plugins

import com.sfh.models.ProfileDTO
import com.sfh.models.Profiles
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

/*
@Serializable
data class ProfileApiItem(
    @SerialName(value = "user_id") val userId: Int,
    @SerialName(value = "user_name") val userName: String,
    @SerialName(value = "user_password") val userPassword: String = "",
    @SerialName(value = "user_height") val userHeight: String,
    @SerialName(value = "user_weight") val userWeight: String,
    @SerialName(value = "user_gender") val userGender: String = "",
    @SerialName(value = "user_token") var userToken: String,
)
*/


fun Application.configureRouting() {
    routing {
        get("/") {
            val userName = call.request.queryParameters["user_name"]
            val userPassword = call.request.queryParameters["user_password"]
            if (userName != null && userPassword !=null)
            {
                val existingProfile = Profiles.getProfile(userName)
                if(existingProfile!=null)
                {
                    if(existingProfile.userPassword == userPassword)
                    {
                        println("All right")
                        call.respond(existingProfile)
                    }
                    else
                    {
                        println("Wrong password")
                        call.respond(message="Wrong password",status=HttpStatusCode.BadRequest)
                    }
                }
                else
                {
                    println("User not exist")
                    call.respond(message="User not exist",status=HttpStatusCode.NotFound)
                }
            }
            else {
                println("Missing login or password")
                call.respond(message = "Missing login or password",status = HttpStatusCode.BadRequest)
            }
        }


        post("/") {
            val profileDTO = call.receive(ProfileDTO::class)
            val existingProfile: ProfileDTO? = Profiles.getProfile(profileDTO.userName)

            if (existingProfile != null) {
                call.respond(status = HttpStatusCode.BadRequest, message = "User with this login exists")
            } else {
                val token = UUID.randomUUID().toString()
                profileDTO.userToken=token
                Profiles.insertProfile(profileDTO)
                call.respond(status = HttpStatusCode.Created, message = profileDTO)
            }
        }
    }
}
/*
val answer = ProfileApiItem(
    userName = "Dmitry",
    userPassword = "123456",
    userHeight = "168",
    userWeight = "68",
    userGender = "M",
    userToken = "E434J49JNFI",
    userId = 1
)
call.respond(message = answer, status = HttpStatusCode.OK)
*/

