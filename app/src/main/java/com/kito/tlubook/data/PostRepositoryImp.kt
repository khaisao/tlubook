package com.kito.tlubook.data

import android.net.Uri
import com.google.firebase.FirebaseException
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.kito.tlubook.domain.repository.AddPostResponse
import com.kito.tlubook.domain.repository.PostRepository
import com.kito.tlubook.core.FireStoreCollection
import com.kito.tlubook.core.UiState
import com.kito.tlubook.domain.model.Post
import com.kito.tlubook.domain.repository.UploadFileResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.File
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepositoryImp @Inject constructor(
    private val database: FirebaseFirestore,
    private val storageReference: StorageReference,
    private val postRef: CollectionReference

) : PostRepository {
    override fun getPostFromFireStore() = callbackFlow {
        val snapshotListener = postRef.orderBy("createAt").addSnapshotListener { snapshot, e ->
            val postsResponse = if (snapshot != null) {
                val books = snapshot.toObjects(Post::class.java)
                UiState.Success(books)
            } else {
                UiState.Failure(e.toString())
            }
            trySend(postsResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }


    override suspend fun addPost(post: Post): AddPostResponse {
        return try {
            val document = database.collection(FireStoreCollection.POST).document()
            post.id = document.id
            postRef.document(document.id).set(post).await()
            UiState.Success(true)
        } catch (e: Exception) {
            UiState.Failure(e.toString())
        }
    }

    override suspend fun uploadSingleFile(filePath: String): UploadFileResponse {
        try {
            val uri: Uri = withContext(Dispatchers.IO) {
                storageReference
                    .child(
                        "post/"
                                + UUID.randomUUID().toString() + ".png"
                    )
                    .putFile(Uri.fromFile(File(filePath)))
                    .await()
                    .storage
                    .downloadUrl
                    .await()
            }
            return UiState.Success(uri.toString())
        } catch (e: FirebaseException) {
            return UiState.Failure("errorUpload: ${e.message}" )
        } catch (e: Exception) {
            return UiState.Failure("errorUpload: ${e.message}" )
        }
    }
}