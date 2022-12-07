package com.kito.tlubook.presentation.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kito.tlubook.R
import com.kito.tlubook.databinding.FragmentNotificationBinding
import com.kito.tlubook.core.base.BaseBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment : BaseBindingFragment<FragmentNotificationBinding>() {
    override fun getLayoutId(): Int =R.layout.fragment_notification


}