package com.visarut.myapplication.data.repository

import com.visarut.myapplication.data.response.CoinDetailResponse
import com.visarut.myapplication.data.response.CoinsResponse

interface CoinRepository {
    suspend fun getCoins(): CoinsResponse
    fun searchCoin(keyWord: String)
    suspend fun getCoinDetail(uuid: String): CoinDetailResponse
}