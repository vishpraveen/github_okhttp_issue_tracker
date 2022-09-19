package io.vishpraveen.demoapp.dependencies

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.vishpraveen.demoapp.R
import io.vishpraveen.demoapp.repository.local_db.DemoAppDatabase
import io.vishpraveen.demoapp.repository.local_db.IssueCommentDao
import io.vishpraveen.demoapp.repository.local_db.IssueDetailDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDatabaseModule {

    @Provides
    @Singleton
    fun provideDemoAppDatabase(@ApplicationContext context: Context): DemoAppDatabase {
        return Room.databaseBuilder(
            context,
            DemoAppDatabase::class.java, context.getString(R.string.app_name)
        ).build()
    }

    @Provides
    @Singleton
    fun provideIssueDetailDao(db: DemoAppDatabase): IssueDetailDao {
        return db.issueDetailDao()
    }

    @Provides
    @Singleton
    fun provideIssueCommentDao(db: DemoAppDatabase): IssueCommentDao {
        return db.issueCommentDao()
    }
}