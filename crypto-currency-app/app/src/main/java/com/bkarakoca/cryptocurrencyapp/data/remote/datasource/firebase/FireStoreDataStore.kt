package com.bkarakoca.cryptocurrencyapp.data.remote.datasource.firebase

import com.bkarakoca.cryptocurrencyapp.application.UserManager
import com.bkarakoca.cryptocurrencyapp.data.remote.api.RemoteDataStore
import com.bkarakoca.cryptocurrencyapp.data.remote.model.datastore.AuthResponse
import com.bkarakoca.cryptocurrencyapp.data.remote.model.datastore.DataStoreResponse
import com.bkarakoca.cryptocurrencyapp.data.remote.model.datastore.crypto.CryptoCoinFireStoreResponse
import com.bkarakoca.cryptocurrencyapp.data.util.UserConstants
import com.bkarakoca.cryptocurrencyapp.internal.util.Failure
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class FireStoreDataStore @Inject constructor(private val gson: Gson) : RemoteDataStore {

    private val dataSource = Firebase.firestore
    private val auth = Firebase.auth

    override suspend fun createUserDocument(email: String): Flow<DataStoreResponse> =
        callbackFlow {
            dataSource.collection(email).document(UserConstants.USER_FAVORITE_CRYPTO_DOCUMENT_NAME)
                .set(hashMapOf<String, Any>(UserConstants.USER_FAVORITE_CRYPTO_LIST_NAME to ArrayList<Any>()))
                .addOnSuccessListener {
                    offer(DataStoreResponse(true))
                    close()
                }
                .addOnFailureListener { e ->
                    close(Failure.DataStoreFailure(e.localizedMessage))
                }

            awaitClose()
        }

    override suspend fun updateUserFavoriteCryptoList(value: Any): Flow<DataStoreResponse> =
        callbackFlow {
            dataSource.collection(UserManager.userEmail)
                .document(UserConstants.USER_FAVORITE_CRYPTO_DOCUMENT_NAME)
                .update(UserConstants.USER_FAVORITE_CRYPTO_LIST_NAME, FieldValue.arrayUnion(value))
                .addOnSuccessListener {
                    offer(DataStoreResponse(true))
                    close()
                }
                .addOnFailureListener { e ->
                    close(Failure.DataStoreFailure(e.localizedMessage))
                }

            awaitClose()
        }

    override suspend fun fetchUserFavoriteCryptoList(): Flow<CryptoCoinFireStoreResponse> =
        callbackFlow {
            dataSource.collection(UserManager.userEmail)
                .document(UserConstants.USER_FAVORITE_CRYPTO_DOCUMENT_NAME)
                .get()
                .addOnSuccessListener { document ->
                    val jsonResponse =
                        gson.toJsonTree(document[UserConstants.USER_FAVORITE_CRYPTO_LIST_NAME])
                    val responseModel =
                        gson.fromJson(jsonResponse, CryptoCoinFireStoreResponse::class.java)
                    if (responseModel.isEmpty()) {
                        close(Failure.DataStoreFailure())
                    } else {
                        offer(responseModel)
                    }
                    close()
                }
                .addOnFailureListener { e ->
                    close(Failure.DataStoreFailure(e.localizedMessage))
                }

            awaitClose()
        }

    override suspend fun postLogin(
        emailText: String,
        passwordText: String
    ): Flow<AuthResponse> = callbackFlow {
        auth.signInWithEmailAndPassword(emailText, passwordText)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    offer(AuthResponse(task.result?.user?.email, task.result?.user?.displayName))
                    close()
                } else {
                    close(Failure.DataStoreFailure(task.exception?.localizedMessage))
                }
            }

        awaitClose()
    }

    override suspend fun postRegister(
        emailText: String,
        passwordText: String
    ): Flow<DataStoreResponse> = callbackFlow {
        auth.createUserWithEmailAndPassword(emailText, passwordText)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    offer(DataStoreResponse(true))
                    close()
                } else {
                    close(Failure.DataStoreFailure(task.exception?.localizedMessage))

                }
            }

        awaitClose()
    }

}