package com.visarut.myapplication.data.repository.apiService

import com.visarut.myapplication.data.response.CoinDetailResponse
import com.visarut.myapplication.data.response.CoinsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/v2/coins")
    suspend fun fetchCoins(
        @Query("offset") offSet: Int,
        @Query("limit") limit: Int
    ): CoinsResponse
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