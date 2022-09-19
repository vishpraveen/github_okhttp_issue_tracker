package io.vishpraveen.demoapp.repository

import io.vishpraveen.demoapp.model.IssueCommentsModel
import io.vishpraveen.demoapp.model.IssueDetailModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubIssueClient {
    @GET("repos/square/okhttp/issues")
    suspend fun fetchIssues(): Response<List<IssueDetailModel>>

    @GET("repos/square/okhttp/issues/{commentId}/comments")
    suspend fun fetchIssueComments(@Path("commentId") id: String): Response<List<IssueCommentsModel>>
}