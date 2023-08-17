package io.vishpraveen.demoapp.dependencies

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.vishpraveen.demoapp.repository.GithubIssueCommentDataSource
import io.vishpraveen.demoapp.repository.GithubIssueCommentRepository
import io.vishpraveen.demoapp.repository.GithubIssueDataSource
import io.vishpraveen.demoapp.repository.GithubIssueRepository
import io.vishpraveen.demoapp.repository.local_db.DemoAppDatabase
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ClientModule {

    @Provides
    @Singleton
    fun provideGithubIssueRepository(retrofit: Retrofit, db: DemoAppDatabase): GithubIssueDataSource {
        return GithubIssueRepository(retrofit, db)
    }

    @Provides
    @Singleton
    fun provideGithubCommentRepository(retrofit: Retrofit, db: DemoAppDatabase): GithubIssueCommentDataSource {
        return GithubIssueCommentRepository(retrofit, db)
    }
}