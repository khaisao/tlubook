package com.kito.tlubook.data

import android.net.Uri
import com.google.firebase.FirebaseException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.kito.tlubook.domain.repository.AddPostResponse
import com.kito.tlubook.domain.repository.PostRepository
import com.kito.tlubook.core.FireStoreCollection
import com.kito.tlubook.core.UiState
import com.kito.tlubook.domain.model.Comment
import com.kito.tlubook.domain.model.Post
import com.kito.tlubook.domain.repository.AddCommentResponse
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

    ) : PostRepository {
    override fun getSnapshotPosts() = callbackFlow {
        val snapshotListener = database.collection(FireStoreCollection.POST).orderBy("createAt")
            .addSnapshotListener { snapshot, e ->
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

    override fun getSnapshotComments(postId: String) = callbackFlow {
        val snapshotListener =
            database.collection(FireStoreCollection.COMMENT).orderBy("createAt")
                .addSnapshotListener { snapshot, e ->
                    val commentsResponse = if (snapshot != null) {
                        val comments = snapshot.toObjects(Comment::class.java)
                        UiState.Success(comments)
                    } else {
                        UiState.Failure(e.toString())
                    }
                    trySend(commentsResponse)
                }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun getStaticPost(id: String): Post? {
        return database.collection(FireStoreCollection.POST)
            .document(id)
            .get()
            .await()
            .toObject(Post::class.java)
    }

    override suspend fun addPost(post: Post): AddPostResponse {
        return try {
            val document = database.collection(FireStoreCollection.POST).document()
            post.id = document.id
            database.collection(FireStoreCollection.POST).document(document.id).set(post).await()
            UiState.Success(true)
        } catch (e: Exception) {
            UiState.Failure(e.toString())
        }
    }

    override suspend fun updatePost(post: Post): AddPostResponse {
        return try {
            val document = database.collection(FireStoreCollection.POST).document(post.id)
            document
                .set(post).await()
            UiState.Success(true)

        } catch (e: Exception) {
            UiState.Failure(e.toString())

        }
    }

    override suspend fun deletePost(post: Post): AddPostResponse {
        return try {
            database.collection(FireStoreCollection.POST).document(post.id)
                .delete().await()
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
            return UiState.Failure("errorUpload: ${e.message}")
        } catch (e: Exception) {
            return UiState.Failure("errorUpload: ${e.message}")
        }
    }

    override suspend fun addComment(comment: Comment): AddCommentResponse {
        return try {
            val document = database.collection(FireStoreCollection.COMMENT).document()
            comment.id = document.id
            database.collection(FireStoreCollection.COMMENT).document(document.id).set(comment)
                .await()
            UiState.Success(true)

        } catch (e: Exception) {
            UiState.Failure(e.toString())
        }
    }
}