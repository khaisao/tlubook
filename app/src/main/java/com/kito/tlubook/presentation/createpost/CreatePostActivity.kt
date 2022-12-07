package com.kito.tlubook.presentation.createpost

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.kito.tlubook.R
import com.kito.tlubook.core.UiState
import com.kito.tlubook.core.base.BaseBindingActivity
import com.kito.tlubook.core.getRealPathFromUri
import com.kito.tlubook.databinding.ActivityCreatePostBinding
import com.kito.tlubook.domain.model.Post
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File


@AndroidEntryPoint
class CreatePostActivity : BaseBindingActivity<ActivityCreatePostBinding>() {
    private val postViewModel: PostViewModel by viewModels()
    lateinit var imageRealPath: String

    override fun getLayoutId(): Int = R.layout.activity_create_post
    override fun addEvent() {
        super.addEvent()
        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val uri = result.data?.data
                    if (uri != null) {
                        imageRealPath = getRealPathFromUri(this, uri) ?: ""
                        val imgFile = File(imageRealPath)
                        if (imgFile.exists()) {
                            val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
                            binding.ivPost.setImageBitmap(myBitmap)
                        }
                    }
                }
            }

        binding.apply {
            tvChooseImage.setOnClickListener {
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                resultLauncher.launch(intent)
            }
            tvUploadPost.setOnClickListener {
                if (File(imageRealPath).isFile) {
                    postViewModel.addPost(Post(
                        id = "",
                        caption = binding.edtCaption.text.toString(),
                        linkImage = imageRealPath,
                        linkVideo = "",
                        createAt = System.currentTimeMillis(),
                        listUserLike = arrayListOf(),
                        likeCount = 0,
                        commentCount = 0,
                        userId = ""
                    ))
                }
            }
        }
    }

    override fun setUpViews() {
        super.setUpViews()
        postViewModel.apply {
            lifecycleScope.launch {
                addPostResponse.collect { state ->
                    when (state) {
                        is UiState.Success -> {
                            Toast.makeText(this@CreatePostActivity, "Success", Toast.LENGTH_SHORT)
                                .show()
                            finish()
                        }
                        is UiState.Loading -> {
                            Toast.makeText(this@CreatePostActivity, "Loading", Toast.LENGTH_SHORT)
                                .show()
                        }

                        else -> {
//                            Toast.makeText(this@CreatePostActivity, "Error", Toast.LENGTH_SHORT)
//                                .show()
//                            finish()
                        }
                    }
                }
            }
        }
    }
}