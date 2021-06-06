package com.goodboi.fragments

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.goodboi.R
import com.goodboi.fragments.viewModel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfilFragment : Fragment() {

    private val MY_CAMERA_REQUEST_CODE = 100
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    val data = null
    val REQUEST_IMAGE_CAPTURE = 1
    var imageId: ImageView? = null


    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
            Log.d("ERR", "Impossible to take a picture, you don't have the right")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageId?.setImageBitmap(imageBitmap)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageId = view?.findViewById(R.id.profilePicture)

        view.findViewById<Button>(R.id.buttonPhoto).setOnClickListener {
            dispatchTakePictureIntent()
        }

        view.findViewById<Button>(R.id.logoutButton).setOnClickListener {
            loginViewModel.logOut()
            findNavController().navigate(R.id.loginFragment)
        }
    }
}