package com.kito.tlubook.ui.activity.login

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.PhoneAuthProvider
import com.kito.tlubook.data.login.user.UserRepository
import com.kito.tlubook.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor (
    private val userRepository: UserRepository
) : ViewModel() {
//    @Inject
//    lateinit var db: FirebaseFirestore
//
//    @Inject
//    lateinit var storage: StorageReference
//
//    @Inject
//    lateinit var currentUser: MutableLiveData<User>

    var phoneNumber =""
    var verifyID=""

    fun sendOTPCode(numberPhone: String, activity: Activity, callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks){
        userRepository.sendOtp(numberPhone,activity,callbacks){
        }
    }

    private val _loginState = MutableLiveData<UiState<Boolean>>()
    val loginState: LiveData<UiState<Boolean>>
        get() = _loginState
    fun verifyOTP(verifyId:String, codeOTP:String){
        userRepository.verifyOTP(verifyId,codeOTP){
            _loginState.value = it
        }
    }

}