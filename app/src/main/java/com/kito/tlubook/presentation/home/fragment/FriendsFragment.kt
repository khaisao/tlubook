package com.kito.tlubook.presentation.home.fragment

import com.kito.tlubook.R
import com.kito.tlubook.databinding.FragmentFriendsBinding
import com.kito.tlubook.core.base.BaseBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FriendsFragment : BaseBindingFragment<FragmentFriendsBinding>() {
    override fun getLayoutId(): Int =R.layout.fragment_friends


}