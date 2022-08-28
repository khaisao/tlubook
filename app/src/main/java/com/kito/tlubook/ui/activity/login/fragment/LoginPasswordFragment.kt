package com.kito.tlubook.ui.activity.login.fragment

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kito.tlubook.R
import com.kito.tlubook.databinding.FragmentLoginPasswordBinding
import com.kito.tlubook.ui.activity.login.LoginViewModel
import com.kito.tlubook.ui.base.BaseBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginPasswordFragment : BaseBindingFragment<FragmentLoginPasswordBinding>() {
    private val viewModel: LoginViewModel by activityViewModels()
    override fun getLayoutId(): Int =R.layout.fragment_login_password
    override fun addEvent() {
        super.addEvent()
        with(binding){
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
            tvNext.setOnClickListener {
                if(edtPassWord.editText?.text.toString().isEmpty()){
                    return@setOnClickListener
                }
                viewModel.password=edtPassWord.editText?.text.toString()
                findNavController().navigate(LoginPasswordFragmentDirections.actionLoginCreatePasswordFragmentToLoginFinishFragment())
            }
        }
    }

}