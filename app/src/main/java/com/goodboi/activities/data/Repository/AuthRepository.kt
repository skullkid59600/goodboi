package com.goodboi.activities.data.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.goodboi.activities.data.dataSource.remote.firebase.FirebaseAuthService
import com.google.firebase.auth.FirebaseUser

class AuthRepository(val firebaseAuthService: FirebaseAuthService) {

    fun getCurrentFirebaseUser() = firebaseAuthService.getCurrentFirebaseUser()

    fun signIn(email: String, password: String): MutableLiveData<FirebaseUser?> {
        val response = MutableLiveData<FirebaseUser?>()
        firebaseAuthService.signIn(email, password)
            .addOnSuccessListener {
                response.value = it.user
            }.addOnFailureListener() {

            }
        return response
    }

    fun signUp(email: String, password: String): MutableLiveData<FirebaseUser?> {
        val response = MutableLiveData<FirebaseUser?>()
        firebaseAuthService.signUp(email, password)
            .addOnSuccessListener {
                response.value = it.user
            }
            .addOnFailureListener {
                Log.d("Erreur SignUp", it.message.toString())
        }
        return response
    }
}