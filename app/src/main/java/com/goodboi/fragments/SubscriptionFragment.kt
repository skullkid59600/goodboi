package com.goodboi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.goodboi.R
import com.goodboi.fragments.viewModel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SubscriptionFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subscription, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.signUpButton).setOnClickListener {

            val editTextMail: TextView? = view.findViewById<TextView>(R.id.mailSignUp)
            val editTextPassword: TextView? = view.findViewById<TextView>(R.id.passwordSignup)
            // get editText values
            loginViewModel.signUp(editTextMail?.text.toString(), editTextPassword?.text.toString())
                .observe(viewLifecycleOwner, Observer {
                    it?.uid
                    findNavController().navigate(R.id.loginFragment)
                })
        }
        view.findViewById<TextView>(R.id.logIn).setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
    }
}

