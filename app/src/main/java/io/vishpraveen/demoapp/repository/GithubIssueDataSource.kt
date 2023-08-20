package io.vishpraveen.demoapp.repository

import io.vishpraveen.demoapp.model.IssueDetailModel
import kotlinx.coroutines.flow.Flow

interface GithubIssueDataSource {
    suspend fun getIssues(): Flow<List<IssueDetailModel>>
}