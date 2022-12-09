package com.kito.tlubook.presentation.home.fragment.home

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.kito.tlubook.R
import com.kito.tlubook.core.UiState
import com.kito.tlubook.databinding.FragmentDetailPostBinding
import com.kito.tlubook.presentation.adapter.CommentAdapter
import com.kito.tlubook.core.base.BaseBindingFragment
import com.kito.tlubook.core.hideSoftKeyboard
import com.kito.tlubook.domain.model.Comment
import com.kito.tlubook.presentation.createpost.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailPostFragment : BaseBindingFragment<FragmentDetailPostBinding>() {

    private val postViewModel: PostViewModel by activityViewModels()
    private val args: DetailPostFragmentArgs by navArgs()


    override fun getLayoutId(): Int = R.layout.fragment_detail_post
    private val adapter: CommentAdapter = CommentAdapter()

    override fun setUpViews() {
        super.setUpViews()
        postViewModel.getComments(args.post.id)
        lifecycleScope.launch {
            postViewModel.commentsResponse.collect { state ->
                when (state) {
                    is UiState.Success -> {
                        Log.d("lkjaewrlkj", "setUpViews: ${state.data}")
                        adapter.submitList(state.data)
                    }
                }
            }
        }
        lifecycleScope.launch {
            postViewModel.addCommentResponse.collect { state ->
                when (state) {
                    is UiState.Success -> {
                        Log.d("lajrljrhl", "setUpViews: succ")
                        Log.d("lajrljrhl", "setUpViews: ${adapter.itemCount - 1}")
                        binding.rv.smoothScrollToPosition(adapter.itemCount - 1)
                    }
                }
            }
        }
    }

    override fun addEvent() {
        super.addEvent()

        with(binding) {
            ivSend.setOnClickListener {
                val comment = Comment(
                    postId = args.post.id,
                    content = edtCommentContent.text.toString(),
                    createAt = System.currentTimeMillis()
                )
                postViewModel.addComment(comment)
                requireContext().hideSoftKeyboard(requireActivity())
                binding.edtCommentContent.text.clear()
                binding.edtCommentContent.clearFocus()
            }
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
            rv.adapter = adapter
        }
    }

}