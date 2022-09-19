package io.vishpraveen.demoapp.repository.local_db

import androidx.room.TypeConverter
import io.vishpraveen.demoapp.model.IssueCommentsModel
import io.vishpraveen.demoapp.model.User
import org.json.JSONObject

class UserTypeConverter {

    @TypeConverter
    fun userToString(user: User): String {
        return JSONObject().apply {
            put(LOGIN, user.login)
            put(AVATAR_URL, user.avatar_url)
        }.toString()
    }

    @TypeConverter
    fun stringToUser(value: String): User {
        val json = JSONObject(value)
        return User(
            login = json.optString(LOGIN),
            avatar_url = json.optString(AVATAR_URL)
        )
    }

    private companion object {
        const val LOGIN = "login"
        const val AVATAR_URL = "avatar_url"
    }
}