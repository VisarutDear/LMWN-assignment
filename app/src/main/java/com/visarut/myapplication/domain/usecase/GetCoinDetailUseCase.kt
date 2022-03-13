package com.visarut.myapplication.domain.usecase

import com.visarut.myapplication.data.repository.CoinRepository
import com.visarut.myapplication.data.response.CoinDetailResponse

class GetCoinDetailUseCase(
    private val coinRepository: CoinRepository
) : BaseUseCase<GetCoinDetailUseCase.Input, CoinDetailResponse>() {

    data class Input(
        val uuid: String
    )

    override suspend fun create(input: Input): CoinDetailResponse =
        coinRepository.getCoinDetail(input.uuid)
}