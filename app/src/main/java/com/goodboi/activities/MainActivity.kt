package com.goodboi.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.goodboi.R
import com.goodboi.activities.data.ImageViewModelFactory
import com.goodboi.activities.data.Repository.ImageRepository
import com.goodboi.databinding.MainActivityBinding
import com.goodboi.fragments.viewModel.ImageViewModel
import com.goodboi.fragments.viewModel.myResponse
import com.goodboi.utils.Constants
import com.goodboi.utils.activityViewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val binding by activityViewBinding(MainActivityBinding::inflate)

    private lateinit var nNavControler: NavController

    //Implement API Images
    private lateinit var imageViewModel: ImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navView : BottomNavigationView = binding.navView
            //permet de naviguer entre les fragments
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        nNavControler = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_classement, R.id.navigation_swipe, R.id.navigation_profile
        ))
        //syncronise texte de l'action bar tout en haut avec le fragment
        setupActionBarWithNavController(nNavControler, appBarConfiguration)
    navView.setupWithNavController(nNavControler)

        val repository = ImageRepository()
        val viewModelFactory = ImageViewModelFactory(repository)
        imageViewModel = ViewModelProvider(this, viewModelFactory).get(ImageViewModel::class.java)
        imageViewModel.getImage()
        myResponse.observe(this, Observer {
            response ->
            Log.d("Response", response.message)
            Log.d("Response", response.status)
        })
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
    }
}