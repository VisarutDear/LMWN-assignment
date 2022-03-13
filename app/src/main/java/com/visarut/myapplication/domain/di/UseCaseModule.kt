package com.visarut.myapplication.domain.di

import com.visarut.myapplication.domain.usecase.GetCoinDetailUseCase
import com.visarut.myapplication.domain.usecase.GetCoinUseCase
import com.visarut.myapplication.domain.usecase.SearchCoinUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single {
        GetCoinUseCase(get())
    }

    single {
        SearchCoinUseCase(get())
    }

    single {
        GetCoinDetailUseCase(get())
    }
}