package com.goodboi.activities.data.Repository

import androidx.lifecycle.MutableLiveData
import com.goodboi.activities.data.dataSource.remote.FirebaseAuthService
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
}