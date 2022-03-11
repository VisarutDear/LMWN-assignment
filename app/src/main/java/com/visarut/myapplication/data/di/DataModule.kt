package com.visarut.myapplication.data.di

import com.visarut.myapplication.data.repository.CoinRepository
import com.visarut.myapplication.data.repository.CoinRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    factory<CoinRepository> { CoinRepositoryImpl(get()) }
}