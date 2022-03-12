package com.visarut.myapplication.data.repository

import android.util.Log
import com.visarut.myapplication.data.repository.apiService.ApiService
import com.visarut.myapplication.data.response.CoinDetailResponse
import com.visarut.myapplication.data.response.CoinsResponse
import com.visarut.myapplication.data.response.Data
import com.visarut.myapplication.data.response.SearchCoinResponse

class CoinRepositoryImpl(private val api: ApiService): CoinRepository {
    override suspend fun getCoins(offSet: Int, limit: Int): CoinsResponse {
        return api.fetchCoins(offSet, limit)
    }

    override suspend fun searchCoin(query: String): SearchCoinResponse {
        return api.searchCoin(query)
    }

    override suspend fun getCoinDetail(uuid: String): CoinDetailResponse {
        Log.d("test1", uuid)
        return api.getCoinDetail(uuid)
    }

}