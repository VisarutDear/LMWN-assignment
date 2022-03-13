package com.visarut.myapplication.view.feature.featureHome.di

import com.visarut.myapplication.view.feature.featureBottomSheet.BottomSheetViewModel
import com.visarut.myapplication.view.feature.featureHome.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureHomeModule = module {
    viewModel {
        HomeViewModel(get(), get())
    }

    viewModel {
        BottomSheetViewModel(get())
    }
}