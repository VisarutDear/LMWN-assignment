package com.visarut.myapplication.domain.usecase

import com.visarut.myapplication.data.repository.CoinRepository
import com.visarut.myapplication.data.response.CoinDetailResponse
import com.visarut.myapplication.data.response.CoinsResponse

class CoinUseCase(
    private val coinRepository: CoinRepository
) {
    suspend fun getCoin(offset: Int, limit: Int): CoinsResponse {
        return coinRepository.getCoins(offset, limit)
    }

    fun searchCoin(keyword: String) {
        return coinRepository.searchCoin(keyword)
    }

    suspend fun getCoinDetail(uuid: String): CoinDetailResponse {
        return coinRepository.getCoinDetail(uuid)
    }
}