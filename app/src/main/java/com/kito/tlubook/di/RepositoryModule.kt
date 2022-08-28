package com.kito.tlubook.di

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.google.gson.Gson
import com.kito.tlubook.data.login.user.AuthRepository
import com.kito.tlubook.data.login.user.AuthRepositoryImp
import com.kito.tlubook.data.model.User
import com.kito.tlubook.util.mutableLiveDataOf
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepository(
        database: FirebaseFirestore,
        auth: FirebaseAuth,
        storageReference:StorageReference,
        appPreferences: SharedPreferences,
        gson: Gson
    ): AuthRepository{
        return AuthRepositoryImp(auth,database,appPreferences,gson)
    }

    @Provides
    @Singleton
    fun provideCurrentUser(): MutableLiveData<User> = mutableLiveDataOf()

}