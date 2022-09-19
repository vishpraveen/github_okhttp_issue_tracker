package io.vishpraveen.demoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "comments")
data class IssueCommentsModel(
    @ColumnInfo(name = "body")
    @SerializedName("body")
    var body: String? = null,
    @ColumnInfo(name = "user")
    @SerializedName("user")
    var user: User? = null,
    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    var updatedAt: String? = null,
)
