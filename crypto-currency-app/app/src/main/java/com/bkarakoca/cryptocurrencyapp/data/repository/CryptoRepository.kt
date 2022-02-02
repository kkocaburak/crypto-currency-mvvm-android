package com.bkarakoca.cryptocurrencyapp.data.repository

import com.bkarakoca.cryptocurrencyapp.data.remote.api.RemoteDataStore
import com.bkarakoca.cryptocurrencyapp.data.remote.datasource.crypto.CryptoRemoteDataSource
import com.bkarakoca.cryptocurrencyapp.data.remote.model.datastore.DataStoreResponse
import com.bkarakoca.cryptocurrencyapp.data.repository.mapper.crypto.CryptoCoinDetailMapper
import com.bkarakoca.cryptocurrencyapp.data.repository.mapper.crypto.CryptoCoinFireStoreMapper
import com.bkarakoca.cryptocurrencyapp.data.repository.mapper.crypto.CryptoCoinListMapper
import com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptodetail.model.CryptoCoinDetailUIModel
import com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptolist.model.CryptoCoinUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoRepository @Inject constructor(
    private val remoteDataSource: CryptoRemoteDataSource,
    private val remoteDataStore: RemoteDataStore,
    private val cryptoCoinListMapper: CryptoCoinListMapper,
    private val cryptoCoinDetailMapper: CryptoCoinDetailMapper,
    private val cryptoCoinFireStoreMapper: CryptoCoinFireStoreMapper,
) {
    suspend fun fetchCryptoCoinList(): Flow<List<CryptoCoinUIModel>> = flow {
        remoteDataSource.fetchCryptoCoinList().collect {
            emit(cryptoCoinListMapper.toUIModel(it))
        }
    }

    suspend fun fetchCryptoCoinDetail(cryptoCoinId: String): Flow<CryptoCoinDetailUIModel> = flow {
        remoteDataSource.fetchCryptoCoinDetail(cryptoCoinId).collect {
            emit(cryptoCoinDetailMapper.toUIModel(it))
        }
    }

    suspend fun setFavoriteCryptoCoin(cryptoCoinUIModel: CryptoCoinUIModel): Flow<DataStoreResponse> {
        return flow {
            remoteDataStore.updateUserFavoriteCryptoList(
                cryptoCoinUIModel
            ).collect {
                emit(it)
            }
        }
    }

    suspend fun fetchCryptoCoinFavoritesList(): Flow<List<CryptoCoinUIModel>> {
        return flow {
            remoteDataStore.fetchUserFavoriteCryptoList()
                .collect {
                    emit(cryptoCoinFireStoreMapper.toUIModel(it))
                }
        }
    }

    suspend fun createCryptoCoinFavoritesList(path: String): Flow<DataStoreResponse> {
        return flow {
            remoteDataStore.createUserDocument(path)
                .collect {
                    emit(it)
                }
        }
    }

}