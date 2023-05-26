package com.arcieri.wagner.mvvm_recipebook.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth(): FirebaseAuth = Firebase.auth

//    @Provides
//    @Singleton
//    fun providesFirebaseFirestore(): FirebaseFirestore = Firebase.firestore
//
//    @Provides
//    @Singleton
//    fun providesFirebaseStorage(): FirebaseStorage = Firebase.storage
}