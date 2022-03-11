package com.visarut.myapplication.data.repository.apiService

import com.visarut.myapplication.data.response.CoinDetailResponse
import com.visarut.myapplication.data.response.CoinsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/v2/coins")
    suspend fun fetchCoins(): CoinsResponse
//
//    @GET("/v2/coins?search={keyWord}")
//    suspend fun searchCoin(
//        @Path("keyWord") keyWord: String
//    ): SearchCoinResponse
//
    @GET("/v2/coin/{uuid}")
    suspend fun getCoinDetail(
        @Path("uuid") uuid: String
    ): CoinDetailResponse
}