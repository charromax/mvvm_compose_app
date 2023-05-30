package com.charr0max.gamermvvmapp.di

import com.charr0max.gamermvvmapp.data.core.Constants.USERS
import com.charr0max.gamermvvmapp.data.core.Constants.USER_IMAGES
import com.charr0max.gamermvvmapp.data.repository.AuthRepositoryImpl
import com.charr0max.gamermvvmapp.data.repository.UsersRepositoryImpl
import com.charr0max.gamermvvmapp.domain.repository.AuthRepository
import com.charr0max.gamermvvmapp.domain.repository.UsersRepository
import com.charr0max.gamermvvmapp.domain.usecase.auth.AuthUseCases
import com.charr0max.gamermvvmapp.domain.usecase.auth.GetCurrentUserUseCase
import com.charr0max.gamermvvmapp.domain.usecase.auth.LoginUseCase
import com.charr0max.gamermvvmapp.domain.usecase.auth.LogoutUseCase
import com.charr0max.gamermvvmapp.domain.usecase.auth.SignUpUseCase
import com.charr0max.gamermvvmapp.domain.usecase.user.CreateUser
import com.charr0max.gamermvvmapp.domain.usecase.user.GetUserInfoById
import com.charr0max.gamermvvmapp.domain.usecase.user.UpdateUser
import com.charr0max.gamermvvmapp.domain.usecase.user.UploadImage
import com.charr0max.gamermvvmapp.domain.usecase.user.UserUseCases
import com.charr0max.gamermvvmapp.presentation.utils.ResultingActivityHandler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFireStore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Singleton
    fun provideStorageUsersRef(storage: FirebaseStorage): StorageReference = storage.reference.child(
        USER_IMAGES)

    @Provides
    @Singleton
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideUsersRepository(usersRef: CollectionReference, storageReference: StorageReference): UsersRepository {
        return UsersRepositoryImpl(usersRef, storageReference)
    }

    @Provides
    @Singleton
    fun provideAuthUseCases(authRepository: AuthRepository): AuthUseCases {
        return AuthUseCases(
            LoginUseCase(authRepository),
            GetCurrentUserUseCase(authRepository),
            LogoutUseCase(authRepository),
            SignUpUseCase(authRepository)
        )
    }

    @Provides
    @Singleton
    fun provideUserUseCases(usersRepository: UsersRepository): UserUseCases {
        return UserUseCases(
            CreateUser(usersRepository),
            UpdateUser(usersRepository),
            UploadImage(usersRepository),
            GetUserInfoById(usersRepository)
        )
    }

    @Provides
    @Singleton
    fun provideResultingActivityHandler(): ResultingActivityHandler = ResultingActivityHandler()





}