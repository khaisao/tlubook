package com.kito.tlubook.ui.activity.login.fragment

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.kito.tlubook.R
import com.kito.tlubook.databinding.FragmentLoginCreatePhoneNumberBinding
import com.kito.tlubook.ui.activity.login.LoginViewModel
import com.kito.tlubook.ui.base.BaseBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginCreatePhoneNumberFragment : BaseBindingFragment<FragmentLoginCreatePhoneNumberBinding>() {
    private val viewModel:LoginViewModel by activityViewModels()
    override fun getLayoutId(): Int =R.layout.fragment_login_create_phone_number
    override fun addEvent() {
        super.addEvent()
        with(binding){
            val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    // This callback will be invoked in two situations:
                    // 1 - Instant verification. In some cases the phone number can be instantly
                    //     verified without needing to send or enter a verification code.
                    // 2 - Auto-retrieval. On some devices Google Play services can automatically
                    //     detect the incoming verification SMS and perform verification without
                    //     user action.
                    Log.d("aewghaerhaerh", "onVerificationCompleted:$credential")
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    // This callback is invoked in an invalid request for verification is made,
                    // for instance if the the phone number format is not valid.
                    Log.d("aewghaerhaerh", "onVerificationFailed: ${e.toString()}")

                    // Show a message and update the UI
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    // The SMS verification code has been sent to the provided phone number, we
                    // now need to ask the user to enter the code and then construct a credential
                    // by combining the code with a verification ID.
                    findNavController().navigate(LoginCreatePhoneNumberFragmentDirections.actionLoginCreatePhoneNumberFragmentToLoginOTPFragment())
                    Log.d("aewghaerhaerh", "onCodeSent:$verificationId")
                    viewModel.verifyID=verificationId


                }
            }
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
            tvNext.setOnClickListener {
                val number = "+84"+edtNumber.text.toString()
                viewModel.phoneNumber=number
//
                viewModel.sendOTPCode(number,requireActivity(),callbacks)
//                abc(number,requireActivity())
            }
        }

    }




}