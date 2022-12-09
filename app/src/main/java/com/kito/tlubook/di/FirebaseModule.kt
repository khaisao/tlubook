package com.kito.tlubook.di


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.kito.tlubook.core.FireStoreCollection
import com.kito.tlubook.core.ROOT_DIRECTORY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFireStoreInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    fun providePostsRef(
        db: FirebaseFirestore
    ) = db.collection(FireStoreCollection.POST)

    @Provides
    fun provideCommentsRef(
        db: FirebaseFirestore
    ) = db.collection(FireStoreCollection.COMMENT)

    @Provides
    @Singleton
    fun provideFirebaseAuthInstance(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseStorageInstance(): StorageReference {
        return FirebaseStorage.getInstance().getReference(ROOT_DIRECTORY)
    }


}