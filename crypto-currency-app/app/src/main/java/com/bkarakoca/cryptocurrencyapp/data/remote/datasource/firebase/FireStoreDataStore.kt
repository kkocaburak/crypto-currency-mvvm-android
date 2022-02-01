package com.bkarakoca.cryptocurrencyapp.data.remote.datasource.firebase

import com.bkarakoca.cryptocurrencyapp.data.remote.api.RemoteDataStore
import com.bkarakoca.cryptocurrencyapp.data.remote.model.datastore.DataStoreResponse
import com.bkarakoca.cryptocurrencyapp.data.remote.model.datastore.crypto.CryptoCoinFireStoreResponse
import com.bkarakoca.cryptocurrencyapp.internal.util.Failure
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@ExperimentalCoroutinesApi
class FireStoreDataStore @Inject constructor(private val gson: Gson) : RemoteDataStore {

    private val dataSource = Firebase.firestore

    override suspend fun createDocument(path: String, value: Any): Flow<DataStoreResponse> {
        return flowOf()
    }

    override suspend fun saveDocument(path: String, value: Any): Flow<DataStoreResponse> =
        callbackFlow {
            dataSource.collection(path).document("favorite_list")
                .update("coins", FieldValue.arrayUnion(value))
                .addOnSuccessListener {
                    offer(DataStoreResponse(true))
                    close()
                }
                .addOnFailureListener { e ->
                    close(Failure.DataStoreFailure(e.localizedMessage))
                }

            awaitClose()
        }

    override suspend fun getDocument(path: String): Flow<CryptoCoinFireStoreResponse> =
        callbackFlow {
            dataSource.collection(path).document("favorite_list")
                .get()
                .addOnSuccessListener { document ->
                    val jsonResponse = gson.toJsonTree(document["coins"])
                    val responseModel =
                        gson.fromJson(jsonResponse, CryptoCoinFireStoreResponse::class.java)
                    offer(responseModel)
                    close()
                }
                .addOnFailureListener { e ->
                    close(Failure.DataStoreFailure(e.localizedMessage))
                }

            awaitClose()
        }
}