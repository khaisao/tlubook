package com.kito.tlubook.presentation.login.fragment

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kito.tlubook.R
import com.kito.tlubook.databinding.FragmentLoginEmailBinding
import com.kito.tlubook.presentation.login.LoginViewModel
import com.kito.tlubook.core.base.BaseBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginEmailFragment : BaseBindingFragment<FragmentLoginEmailBinding>() {
    private val viewModel: LoginViewModel by activityViewModels()
    override fun getLayoutId(): Int =R.layout.fragment_login_email
    override fun addEvent() {
        super.addEvent()
        with(binding){
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
            tvNext.setOnClickListener {
                viewModel.email= edtEmail.editText?.text.toString()
                findNavController().navigate(LoginEmailFragmentDirections.actionLoginEmailFragmentToLoginCreatePasswordFragment())
            }
        }

    }




}