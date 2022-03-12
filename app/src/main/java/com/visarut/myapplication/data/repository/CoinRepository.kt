package com.visarut.myapplication.data.repository

import com.visarut.myapplication.data.response.CoinDetailResponse
import com.visarut.myapplication.data.response.CoinsResponse
import com.visarut.myapplication.data.response.SearchCoinResponse

interface CoinRepository {
    suspend fun getCoins(offSet: Int, limit: Int): CoinsResponse
    suspend fun searchCoin(query: String): SearchCoinResponse
    suspend fun getCoinDetail(uuid: String): CoinDetailResponse
}