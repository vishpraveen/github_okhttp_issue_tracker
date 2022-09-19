package io.vishpraveen.demoapp.model

import androidx.room.*
import io.vishpraveen.demoapp.repository.local_db.CommentTypeConverter

@Entity(tableName = "issue_comments")
data class IssueCommentsResponse(
    @PrimaryKey
    @ColumnInfo(name = "cid")
    var cid: String,

    @ColumnInfo(name = "comments")
    @TypeConverters(CommentTypeConverter::class)
    var comments: List<IssueCommentsModel> = listOf()
)
