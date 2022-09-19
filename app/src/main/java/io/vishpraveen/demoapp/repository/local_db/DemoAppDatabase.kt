package io.vishpraveen.demoapp.repository.local_db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.vishpraveen.demoapp.model.IssueCommentsResponse
import io.vishpraveen.demoapp.model.IssueDetailModel
import io.vishpraveen.demoapp.model.User

@Database(entities = [IssueDetailModel::class, IssueCommentsResponse::class], version = 1)
@TypeConverters(UserTypeConverter::class, CommentTypeConverter::class)
abstract class DemoAppDatabase: RoomDatabase() {

    abstract fun issueDetailDao(): IssueDetailDao
    abstract fun issueCommentDao(): IssueCommentDao

}