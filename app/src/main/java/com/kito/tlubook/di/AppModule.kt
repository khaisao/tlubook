package com.kito.tlubook.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.kito.tlubook.core.SharedPrefConstants
import com.kito.tlubook.domain.repository.AuthRepository
import com.kito.tlubook.domain.repository.PostRepository
import com.kito.tlubook.domain.use_case.*
import com.kito.tlubook.domain.use_case.auth.GetSnapshotUser
import com.kito.tlubook.domain.use_case.post.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            SharedPrefConstants.LOCAL_SHARED_PREF,
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providePostUseCases(
        repo: PostRepository
    ) = PostUseCase(
        getSnapshotPosts = GetSnapshotPosts(repo),
        getSnapshotComments = GetSnapshotComments(repo = repo),
        addPost = AddPost(repo),
        updatePost = UpdatePost(repo = repo),
        deletePost = DeletePost(repo),
        uploadSingleFile = UploadSingleFile(repo),
        addComment = AddComment(repo),
    )

    @Provides
    @Singleton
    fun provideAuthUseCases(
        repo: AuthRepository
    ) = AuthUseCase(
        getSnapshotUser = GetSnapshotUser(repo)
    )
}