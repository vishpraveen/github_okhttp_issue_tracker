package io.vishpraveen.demoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import io.vishpraveen.demoapp.repository.local_db.UserTypeConverter
import org.json.JSONObject

@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "login")
    @SerializedName("login")
    var login: String? = null,
    @ColumnInfo(name = "avatar_url")
    @SerializedName("avatar_url")
    var avatar_url: String? = null,
) {
}
