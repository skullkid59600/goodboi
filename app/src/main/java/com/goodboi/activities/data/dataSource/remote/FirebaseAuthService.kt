package com.goodboi.activities.data.dataSource.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class FirebaseAuthService {

    private val auth: FirebaseAuth = Firebase.auth

    fun getCurrentFirebaseUser() = auth.currentUser

    fun signIn(email: String, password: String) = auth.signInWithEmailAndPassword(email, password)

    fun signUp(email: String, password: String) = auth.createUserWithEmailAndPassword(email, password)
}