package com.mustafa.newsapp.extensions

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.mustafa.newsapp.R

fun Activity.findNavController(): NavController {
    val navHostFragment =
        (this as AppCompatActivity).supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
    return navHostFragment.navController
}