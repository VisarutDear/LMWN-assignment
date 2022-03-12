package com.visarut.myapplication.domain.usecase

import com.visarut.myapplication.data.repository.CoinRepository
import com.visarut.myapplication.data.response.CoinDetailResponse
import com.visarut.myapplication.data.response.CoinsResponse
import com.visarut.myapplication.data.response.SearchCoinResponse

class CoinUseCase(
    private val coinRepository: CoinRepository
) {
    suspend fun getCoin(offset: Int, limit: Int): CoinsResponse {
        return coinRepository.getCoins(offset, limit)
    }

    suspend fun searchCoin(query: String): SearchCoinResponse {
        return coinRepository.searchCoin(query)
    }

    suspend fun getCoinDetail(uuid: String): CoinDetailResponse {
        return coinRepository.getCoinDetail(uuid)
    }
}