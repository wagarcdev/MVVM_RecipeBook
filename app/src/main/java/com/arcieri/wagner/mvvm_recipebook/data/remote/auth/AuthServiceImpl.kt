package com.arcieri.wagner.mvvm_recipebook.data.remote.auth

import com.arcieri.wagner.mvvm_recipebook.model.AuthError
import com.arcieri.wagner.mvvm_recipebook.model.Response
import com.google.firebase.auth.*
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthServiceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthService {

    override val currentUserId: String?
        get() = firebaseAuth.currentUser?.uid

    override val isUserAnonymous: Boolean
        get() = firebaseAuth.currentUser?.isAnonymous ?: true

    override suspend fun createAnonymousAccount() {
        firebaseAuth.signInAnonymously().await()
    }

    override suspend fun oneTapSignIn(tokenId: String): Response<Unit, AuthError> {
        return try {
            val credential = GoogleAuthProvider.getCredential(tokenId, null)
            val authResult = firebaseAuth.signInWithCredential(credential).await()

            if (authResult?.user?.uid != null) {
                val id = authResult.user!!.uid

               /** TODO HANDLE ID */

                Response.Success(data = Unit)
            } else throw Exception()
        } catch (e: Exception) {
            e.printStackTrace()
            when (e) {
                is FirebaseAuthUserCollisionException -> {
                    Response.Error(type = AuthError.CredentialCollision)
                }
                else -> Response.Error(type = AuthError.Unknown)
            }
        }
    }

    override suspend fun signIn(
        email: String,
        password: String
    ): Response<Unit, AuthError> {
        return try {
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()

            if (authResult?.user?.uid != null) {
                val id = authResult.user!!.uid

                /** TODO HANDLE ID */


                Response.Success(data = Unit)
            } else throw Exception()
        } catch (e: Exception) {
            e.printStackTrace()
            when (e) {
                is FirebaseAuthInvalidUserException -> {
                    Response.Error(type = AuthError.AccountNotRegistered)
                }
                is FirebaseAuthInvalidCredentialsException -> {
                    Response.Error(type = AuthError.WrongPassword)
                }
                else -> Response.Error(type = AuthError.Unknown)
            }
        }
    }

    override suspend fun signUp(
        email: String,
        password: String
    ): Response<Unit, AuthError> {
        return try {
            val credential = EmailAuthProvider.getCredential(email, password)
            val authResult = firebaseAuth.currentUser?.linkWithCredential(credential)?.await()

            if (authResult?.user?.uid != null) {
                val id = authResult.user!!.uid

                /** TODO HANDLE ID */

                Response.Success(data = Unit)
            } else throw Exception()
        } catch (e: Exception) {
            e.printStackTrace()
            when (e) {
                is FirebaseAuthUserCollisionException -> {
                    Response.Error(type = AuthError.CredentialCollision)
                }
                is FirebaseAuthWeakPasswordException -> {
                    Response.Error(type = AuthError.WeakPassword)
                }
                else -> Response.Error(type = AuthError.Unknown)
            }
        }
    }

    override suspend fun sendRecoverPasswordEmail(
        email: String
    ): Response<Unit, AuthError> = try {
        firebaseAuth.sendPasswordResetEmail(email).await()
        Response.Success(data = Unit)
    } catch (e: Exception) {
        e.printStackTrace()
        when (e) {
            is FirebaseAuthInvalidUserException -> {
                Response.Error(type = AuthError.AccountNotRegistered)
            }
            else -> Response.Error(type = AuthError.Unknown)
        }
    }

    override suspend fun signOut() {
        try {
            if (firebaseAuth.currentUser!!.isAnonymous)
                firebaseAuth.currentUser!!.delete()

            firebaseAuth.signOut()
            createAnonymousAccount()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
//
//    private suspend fun createUserProfile(authResult: AuthResult) {
//        val id = authResult.user!!.uid
//
//        val email = authResult.user?.email ?: ""
//
//        val nickname = authResult.user?.email?.substringBefore(delimiter = "@")
//            ?: "user_${System.currentTimeMillis()}"
//
//        val name = authResult.additionalUserInfo?.profile?.get("name") ?: "UB User"
//        val imageUrl = authResult.additionalUserInfo?.profile?.get("picture") ?: ""
//
//
//        /** TODO create user profile */
//
//
//        userProfileService.createUserProfile(userProfile = newUserProfile)
//    }
}