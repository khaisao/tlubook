package com.kito.tlubook.data.login.user

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.kito.tlubook.util.UiState
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UserRepositoryImp(


) : UserRepository {
 @Inject
 lateinit var db:FirebaseFirestore
// @Inject
// lateinit var auth: FirebaseAuth
 @Inject
 lateinit var storageReference:StorageReference
    override fun sendOtp(numberPhone: String,activity: Activity,callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks, result: (UiState<Nothing>) -> Unit) {
        val auth = FirebaseAuth.getInstance()
//        auth.firebaseAuthSettings.setAppVerificationDisabledForTesting(true)
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(numberPhone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callbacks)// Timeout and unit
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    override fun verifyOTP(verifyId:String, codeOTP:String,result: (UiState<Boolean>) -> Unit) {
        val phoneAthCredential = PhoneAuthProvider.getCredential(verifyId,codeOTP)
        FirebaseAuth.getInstance().signInWithCredential(phoneAthCredential)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    result.invoke(
                        UiState.Success(true)
                    )
                    Log.d("awegawegaewg", "verifyOTP: ok")
                } else {
                    result.invoke(
                        UiState.Failure("False")
                    )
                    Log.d("awegawegaewg", "verifyOTP: falis")

                }
            }
    }
}