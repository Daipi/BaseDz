package com.daipi.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_navigation)
    }

    override fun onSupportNavigateUp(): Boolean =
        findNavController(R.id.my_nav_host_fragment).navigateUp()
}