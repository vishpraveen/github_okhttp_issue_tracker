package io.vishpraveen.demoapp.repository

import android.util.Log
import io.vishpraveen.demoapp.model.IssueDetailModel
import io.vishpraveen.demoapp.repository.local_db.DemoAppDatabase
import io.vishpraveen.demoapp.repository.local_db.IssueDetailDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject

class GithubIssueRepository @Inject constructor(
    retrofit: Retrofit,
    db:DemoAppDatabase
): GithubIssueDataSource {
    private val client = retrofit.create(GithubIssueClient::class.java)
    private val issueDetailDao: IssueDetailDao by lazy { db.issueDetailDao() }

    override suspend fun getIssues(): Flow<List<IssueDetailModel>> = flow {
        issueDetailDao.getAllIssues().collect {
            if(it.isEmpty()) {
                getIssuesFromRemote()
            } else {
                emit(it)
            }
        }
    }

    private suspend fun getIssuesFromRemote() {
        try {
            val result = client.fetchIssues()
            if (result.isSuccessful)
                result.body()?.forEach {
                    issueDetailDao.insert(it)
                }
        } catch (ex: Exception) {
            Log.e(this@GithubIssueRepository::class.simpleName, "getIssuesFromRemote Error: ${ex.message}")
        }
    }
}