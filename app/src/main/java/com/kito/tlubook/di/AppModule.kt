package com.kito.tlubook.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.kito.tlubook.core.SharedPrefConstants
import com.kito.tlubook.domain.repository.PostRepository
import com.kito.tlubook.domain.use_case.AddPost
import com.kito.tlubook.domain.use_case.GetPosts
import com.kito.tlubook.domain.use_case.UploadSingleFile
import com.kito.tlubook.domain.use_case.UseCases
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
    fun provideUseCases(
        repo: PostRepository
    ) = UseCases(
        getPosts = GetPosts(repo),
        addPost = AddPost(repo),
        uploadSingleFile = UploadSingleFile(repo)
    )

}