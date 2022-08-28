package com.kito.tlubook.ui.activity.login.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kito.tlubook.R
import com.kito.tlubook.databinding.FragmentLoginCreateNameBinding
import com.kito.tlubook.databinding.FragmentLoginCreatePasswordBinding
import com.kito.tlubook.databinding.FragmentLoginOtpBinding
import com.kito.tlubook.ui.activity.login.LoginViewModel
import com.kito.tlubook.ui.base.BaseBindingFragment
import com.kito.tlubook.util.UiState
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class LoginOTPFragment : BaseBindingFragment<FragmentLoginOtpBinding>() {
    private val viewModel: LoginViewModel by activityViewModels()
    override fun getLayoutId(): Int =R.layout.fragment_login_otp
    override fun addEvent() {
        super.addEvent()
        with(binding){
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
            tvNext.setOnClickListener {
                if(edtOtp.text.toString().isEmpty()){
                    return@setOnClickListener
                }
                viewModel.verifyOTP(viewModel.verifyID,binding.edtOtp.text.toString())
            }
        }
    }

    override fun setUpViews() {
        super.setUpViews()
        with(binding){
            tvPhoneNumber.text=viewModel.phoneNumber
        }
        viewModel.loginState.observe(viewLifecycleOwner){
            state ->
            when(state){
                is UiState.Success -> {
                    findNavController().navigate(LoginOTPFragmentDirections.actionLoginOTPFragmentToLoginCreatePasswordFragment())
                }
                is UiState.Failure ->{
                    Toasty.error(requireContext(), "Wrong OTP", Toast.LENGTH_SHORT, true).show();

                }
                else -> {}
            }
        }
    }

}