package com.visarut.myapplication.data.repository

import android.util.Log
import com.visarut.myapplication.data.repository.apiService.ApiService
import com.visarut.myapplication.data.response.CoinDetailResponse
import com.visarut.myapplication.data.response.CoinsResponse
import com.visarut.myapplication.data.response.Data

class CoinRepositoryImpl(private val api: ApiService): CoinRepository {
    override suspend fun getCoins(): CoinsResponse {
        return api.fetchCoins()
    }

    override fun searchCoin(keyword: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getCoinDetail(uuid: String): CoinDetailResponse {
        Log.d("test1", uuid)
        return api.getCoinDetail(uuid)
    }

}