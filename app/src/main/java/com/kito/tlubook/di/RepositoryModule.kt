package com.kito.tlubook.di

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.kito.tlubook.data.login.user.UserRepository
import com.kito.tlubook.data.login.user.UserRepositoryImp
import com.kito.tlubook.data.model.User
import com.kito.tlubook.util.mutableLiveDataOf
import dagger.Binds
import dagger.BindsInstance
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

    ): UserRepository{
        return UserRepositoryImp()
    }

    @Provides
    @Singleton
    fun provideCurrentUser(): MutableLiveData<User> = mutableLiveDataOf()

}