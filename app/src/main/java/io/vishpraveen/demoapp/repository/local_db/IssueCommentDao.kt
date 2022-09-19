package io.vishpraveen.demoapp.repository.local_db

import androidx.annotation.WorkerThread
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.vishpraveen.demoapp.model.IssueCommentsModel
import io.vishpraveen.demoapp.model.IssueCommentsResponse
import io.vishpraveen.demoapp.model.IssueDetailModel
import kotlinx.coroutines.flow.Flow

@Dao
interface IssueCommentDao {
    @WorkerThread
    @Query("SELECT * from issue_comments where cid = :commentId")
    fun getAllComments(commentId: String): Flow<IssueCommentsResponse?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @WorkerThread
    fun save(item: IssueCommentsResponse)
}