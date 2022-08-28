package com.kito.tlubook.ui.activity.home

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kito.tlubook.R
import com.kito.tlubook.databinding.ActivityHomeBinding
import com.kito.tlubook.ui.base.BaseBindingActivity

class HomeActivity : BaseBindingActivity<ActivityHomeBinding>() {
    private lateinit var navController: NavController

    override fun getLayoutId(): Int = R.layout.activity_home

    override fun setUpViews() {
        super.setUpViews()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        binding.bottomNavigationView.setupWithNavController(navController)
    }

}