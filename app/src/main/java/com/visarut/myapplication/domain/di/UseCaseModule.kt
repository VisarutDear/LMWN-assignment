package com.visarut.myapplication.domain.di

import com.visarut.myapplication.domain.usecase.CoinUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single {
        CoinUseCase(get())
    }
}