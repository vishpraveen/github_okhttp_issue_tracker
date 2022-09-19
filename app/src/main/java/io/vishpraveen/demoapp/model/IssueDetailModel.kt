package io.vishpraveen.demoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "issue_detail")
data class IssueDetailModel(
    @PrimaryKey(autoGenerate = true) val iid: Int,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String? = null,
    @ColumnInfo(name = "body")
    @SerializedName("body")
    var body: String? = null,
    @ColumnInfo(name = "user")
    @SerializedName("user")
    var user: User? = null,
    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    var updatedAt: String? = null,
    @ColumnInfo(name = "comments_url")
    @SerializedName("comments_url")
    var commentsUrl: String? = null
)
