package com.charr0max.gamermvvmapp.di

import com.charr0max.gamermvvmapp.data.core.Constants.POSTS
import com.charr0max.gamermvvmapp.data.core.Constants.POST_IMAGES
import com.charr0max.gamermvvmapp.data.core.Constants.USERS
import com.charr0max.gamermvvmapp.data.core.Constants.USER_IMAGES
import com.charr0max.gamermvvmapp.data.repository.AuthRepositoryImpl
import com.charr0max.gamermvvmapp.data.repository.PostRepositoryImpl
import com.charr0max.gamermvvmapp.data.repository.UsersRepositoryImpl
import com.charr0max.gamermvvmapp.domain.repository.AuthRepository
import com.charr0max.gamermvvmapp.domain.repository.PostRepository
import com.charr0max.gamermvvmapp.domain.repository.UsersRepository
import com.charr0max.gamermvvmapp.domain.usecase.auth.AuthUseCases
import com.charr0max.gamermvvmapp.domain.usecase.auth.GetCurrentUserUseCase
import com.charr0max.gamermvvmapp.domain.usecase.auth.LoginUseCase
import com.charr0max.gamermvvmapp.domain.usecase.auth.LogoutUseCase
import com.charr0max.gamermvvmapp.domain.usecase.auth.SignUpUseCase
import com.charr0max.gamermvvmapp.domain.usecase.post.CreatePost
import com.charr0max.gamermvvmapp.domain.usecase.post.GetPosts
import com.charr0max.gamermvvmapp.domain.usecase.post.PostUseCases
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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
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
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Named(USERS)
    @Singleton
    fun provideStorageUsersRef(storage: FirebaseStorage): StorageReference =
        storage.reference.child(
            USER_IMAGES
        )

    @Provides
    @Named(USERS)
    @Singleton
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

    @Provides
    @Named(POSTS)
    @Singleton
    fun provideStoragePostRef(storage: FirebaseStorage): StorageReference = storage.reference.child(
        POST_IMAGES
    )

    @Provides
    @Named(POSTS)
    @Singleton
    fun providePostsRef(db: FirebaseFirestore): CollectionReference = db.collection(POSTS)

    @Provides
    @Singleton
    fun provideAuthRepository(impl:AuthRepositoryImpl): AuthRepository = impl

    @Provides
    @Singleton
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    @Singleton
    fun providePostsRepository(impl: PostRepositoryImpl): PostRepository = impl

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
    fun providePostUseCases(postRepository: PostRepository): PostUseCases {
        return PostUseCases(CreatePost(postRepository), GetPosts(postRepository))
    }

    @Provides
    @Singleton
    fun provideResultingActivityHandler(): ResultingActivityHandler = ResultingActivityHandler()

}