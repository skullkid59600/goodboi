package com.goodboi.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.location.Geocoder
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.goodboi.R
import com.goodboi.databinding.FragmentProfileBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import com.goodboi.fragments.viewModel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfilFragment : Fragment(), EasyPermissions.PermissionCallbacks {

    companion object {
        const val PERMISSION_LOCATION_REQUEST_CODE = 1
    }
    private val MY_CAMERA_REQUEST_CODE = 100
    private val loginViewModel: LoginViewModel by viewModel()

    val data = null
    val REQUEST_IMAGE_CAPTURE = 1
    var imageId: ImageView? = null
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

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

    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageId = view.findViewById(R.id.profilePicture)

        view.findViewById<Button>(R.id.buttonPhoto).setOnClickListener {
            dispatchTakePictureIntent()
        }

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())

        binding.PosButton.setOnClickListener {
            if (hasLocationPermission()) {
                fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                    Log.d("ProfileFragment", location.latitude.toString())
                    Log.d("ProfileFragment", location.longitude.toString())
                }
            } else {
                requestLocationPermission()
            }
        }

        view.findViewById<Button>(R.id.logoutButton).setOnClickListener {
            loginViewModel.logOut()
            findNavController().navigate(R.id.loginFragment)
        }
    }

    private fun hasLocationPermission() =
        EasyPermissions.hasPermissions(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )

    private fun requestLocationPermission() {
        EasyPermissions.requestPermissions(
            this,
            "This application cannot work without Location Permission.",
            PERMISSION_LOCATION_REQUEST_CODE,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms.first())) {
            SettingsDialog.Builder(requireActivity()).build().show()
        } else {
            requestLocationPermission()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        Toast.makeText(
            requireContext(),
            "Permission Granted!",
            Toast.LENGTH_SHORT
        ).show()
    }
}