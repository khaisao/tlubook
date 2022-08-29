package com.kito.tlubook.ui.activity.home.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kito.tlubook.R
import com.kito.tlubook.data.model.Post
import com.kito.tlubook.databinding.FragmentHomeBinding
import com.kito.tlubook.ui.adapter.PostAdapter
import com.kito.tlubook.ui.base.BaseBindingFragment


class HomeFragment : BaseBindingFragment<FragmentHomeBinding>() {
    private val adapter: PostAdapter by lazy {
        PostAdapter(onCommentClick = {
//            findNavController().navigate(
//                HomeFragmentDirections.actionHomeFragmentToDetailPostFragment()
//            )
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailPostFragment())
        })
    }

    override fun getLayoutId(): Int = R.layout.fragment_home
    override fun setUpViews() {
        super.setUpViews()
        with(binding) {
            val abc = arrayListOf<Post>()
            for (i in 1..10) {
                val post = Post(id = "2")
                abc.add(post)
            }

            rv.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rv.adapter = adapter
            adapter.listPost = abc
            adapter.notifyDataSetChanged()
        }
    }

}