package com.kito.tlubook.presentation

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.kito.tlubook.R
import com.kito.tlubook.databinding.ActivitySplashBinding
import com.kito.tlubook.presentation.login.LoginActivity
import com.kito.tlubook.core.base.BaseBindingActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseBindingActivity<ActivitySplashBinding>() {
    override fun getLayoutId(): Int =R.layout.activity_splash
    override fun setUpViews() {
        super.setUpViews()
        lifecycleScope.launch {
            delay(500)
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }
    }

}