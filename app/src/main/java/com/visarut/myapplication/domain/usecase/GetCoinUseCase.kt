package com.visarut.myapplication.domain.usecase

import com.visarut.myapplication.data.repository.CoinRepository
import com.visarut.myapplication.data.response.CoinDetailResponse
import com.visarut.myapplication.data.response.CoinsResponse
import com.visarut.myapplication.data.response.SearchCoinResponse

class GetCoinUseCase(
    private val coinRepository: CoinRepository
) : BaseUseCase<GetCoinUseCase.Input, CoinsResponse>() {

    suspend fun getCoinDetail(uuid: String): CoinDetailResponse {
        return coinRepository.getCoinDetail(uuid)
    }

    data class Input(
        val offset: Int,
        val limit: Int,
    )

    override suspend fun create(input: Input): CoinsResponse =
        coinRepository.getCoins(input.offset, input.limit)
}