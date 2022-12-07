package com.kito.tlubook.presentation.home.fragment.home

import android.content.Intent
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kito.tlubook.R
import com.kito.tlubook.core.UiState
import com.kito.tlubook.core.base.BaseBindingFragment
import com.kito.tlubook.databinding.FragmentHomeBinding
import com.kito.tlubook.domain.model.Post
import com.kito.tlubook.presentation.adapter.PostAdapter
import com.kito.tlubook.presentation.createpost.CreatePostActivity
import com.kito.tlubook.presentation.createpost.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseBindingFragment<FragmentHomeBinding>() {
    private val adapter: PostAdapter by lazy {
        PostAdapter(onCommentClick = {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailPostFragment())
        })
    }

    private val postViewModel: PostViewModel by activityViewModels()


    override fun getLayoutId(): Int = R.layout.fragment_home
    override fun setUpViews() {
        super.setUpViews()
        with(binding) {
            rv.adapter = adapter
        }
        with(postViewModel) {
            lifecycleScope.launch {
                postsResponse.collect { state ->
                    when (state) {

                        is UiState.Success ->{
                            Log.d("lkjaghlkajrhljkaerh", "setUpViews: ${state.data}")
                            adapter.submitList(state.data)
                        }
                    }
                }
            }
        }
    }

    override fun addEvent() {
        binding.tvCreatePost.setOnClickListener {
            val intent = Intent(requireContext(), CreatePostActivity::class.java)
            startActivity(intent)
        }
    }


}