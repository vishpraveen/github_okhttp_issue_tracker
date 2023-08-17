package io.vishpraveen.demoapp.repository

import io.vishpraveen.demoapp.model.IssueCommentsModel
import kotlinx.coroutines.flow.Flow

interface GithubIssueCommentDataSource {
    suspend fun getComments(commentId: String): Flow<List<IssueCommentsModel>?>
}