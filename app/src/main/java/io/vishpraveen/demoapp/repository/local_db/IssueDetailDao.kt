package io.vishpraveen.demoapp.repository.local_db

import androidx.annotation.WorkerThread
import androidx.room.Dao
import androidx.room.Query
import io.vishpraveen.demoapp.model.IssueDetailModel
import kotlinx.coroutines.flow.Flow

@Dao
interface IssueDetailDao : BaseDao<IssueDetailModel> {

    @WorkerThread
    @Query("SELECT * from issue_detail")
    fun getAllIssues(): Flow<List<IssueDetailModel>>
}