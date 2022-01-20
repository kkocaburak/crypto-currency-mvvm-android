package com.bkarakoca.cryptocurrencyapp.data.remote.datasource.firebase

import com.bkarakoca.cryptocurrencyapp.data.remote.api.RemoteDataStore
import com.bkarakoca.cryptocurrencyapp.data.remote.model.datastore.DataStoreResponse
import com.bkarakoca.cryptocurrencyapp.data.remote.model.datastore.crypto.CryptoCoinFireStoreModel
import com.bkarakoca.cryptocurrencyapp.internal.util.Failure
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

    override suspend fun saveDocument(path: String, value: Any): Flow<DataStoreResponse> =
        callbackFlow {
            val listener = dataSource.collection(path).document("favorite_list")
                .update("coins", FieldValue.arrayUnion(value))
                .addOnSuccessListener {
                    offer(DataStoreResponse(true))
                }
                .addOnFailureListener {
                    offer(DataStoreResponse(false))
                }

            awaitClose { listener.isComplete }
        }

    override suspend fun getDocument(path: String): Flow<CryptoCoinFireStoreModel> =
        callbackFlow {
            val listener = dataSource.collection(path).document("favorite_list")
                .get()
                .addOnSuccessListener {
                    val jsonResponse = gson.toJsonTree(it["coins"])
                    val responseModel = gson.fromJson(jsonResponse, CryptoCoinFireStoreModel::class.java)
                    offer(responseModel)
                }.addOnFailureListener { e ->
                    throw Failure.DataStoreFailure(e.localizedMessage)
                }

            awaitClose { listener.isComplete }
        }
}