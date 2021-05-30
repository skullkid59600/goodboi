package com.goodboi.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.goodboi.R
import com.goodboi.fragments.viewModel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModel()

    private val permissionResultLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { map ->
            if (!map.values.contains(false)) {
                getDocumentResultLauncher.launch("image/jpeg | image/jpg | image/png")
            }
        }

    private val getDocumentResultLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                Log.d("MyURI", uri.path!!)
            }
        }

    private val takePictureResultLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) {
            if (it) {

            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_login, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // When the user click the button
        view.findViewById<TextView>(R.id.buttonSignIn).setOnClickListener {

            val editTextMail:TextView? = view.findViewById<TextView>(R.id.emailLogin)
            val editTextPassword:TextView? = view.findViewById<TextView>(R.id.passwordLogin)
            // get editText values
            loginViewModel.signIn(editTextMail?.text.toString(), editTextPassword?.text.toString())
                .observe(viewLifecycleOwner, Observer {
                    it?.uid
                    findNavController().navigate(R.id.swipeFragment)
                })
        }

        view.findViewById<TextView>(R.id.SignUp).setOnClickListener {
            findNavController().navigate(R.id.subscriptionFragment)
        }
    }
}