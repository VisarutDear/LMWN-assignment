package com.visarut.myapplication.domain.usecase

import com.visarut.myapplication.data.repository.CoinRepository
import com.visarut.myapplication.data.response.CoinDetailResponse
import com.visarut.myapplication.data.response.CoinsResponse
import com.visarut.myapplication.data.response.SearchCoinResponse

class SearchCoinUseCase(
    private val coinRepository: CoinRepository
) : BaseUseCase<SearchCoinUseCase.Input, SearchCoinResponse>() {

    data class Input(
        val query: String
    )
    override suspend fun create(input: Input): SearchCoinResponse =
        coinRepository.searchCoin(input.query)
}