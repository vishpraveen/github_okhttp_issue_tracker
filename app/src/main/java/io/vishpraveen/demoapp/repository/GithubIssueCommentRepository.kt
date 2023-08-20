package io.vishpraveen.demoapp.repository

import android.text.TextUtils.isEmpty
import android.util.Log
import io.vishpraveen.demoapp.model.IssueCommentsModel
import io.vishpraveen.demoapp.model.IssueCommentsResponse
import io.vishpraveen.demoapp.repository.local_db.DemoAppDatabase
import io.vishpraveen.demoapp.repository.local_db.IssueCommentDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject

class GithubIssueCommentRepository @Inject constructor(
    retrofit: Retrofit,
    db:DemoAppDatabase
): GithubIssueCommentDataSource {
    private val client = retrofit.create(GithubIssueClient::class.java)
    private val issueCommentDao: IssueCommentDao by lazy { db.issueCommentDao() }

    override suspend fun getComments(commentId: String): Flow<List<IssueCommentsModel>> = flow {
        issueCommentDao.getAllComments(commentId).collect {
            if(it == null) {
                getCommentsFromRemote(commentId)
            } else emit(it.comments)
        }
    }

    private suspend fun getCommentsFromRemote(commentId: String) {
        try {
            val result = client.fetchIssueComments(commentId)
            if (result.isSuccessful)
                result.body()?.let {
                    issueCommentDao.save(IssueCommentsResponse(commentId, it))
                }
        } catch (ex: Exception) {
            Log.e(this@GithubIssueCommentRepository::class.simpleName, "getIssuesFromRemote Error: ${ex.message}")
        }
    }
}