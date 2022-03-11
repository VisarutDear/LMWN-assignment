package com.visarut.myapplication.view.feature.featureBottomSheet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visarut.myapplication.data.response.CoinDetail
import com.visarut.myapplication.domain.usecase.CoinUseCase
import kotlinx.coroutines.launch
import java.util.*

class BottomSheetViewModel(
    private val coinUseCase: CoinUseCase
) : ViewModel() {

    var coinList: MutableLiveData<CoinDetail> = MutableLiveData()

    fun fetchCoinDetail(coinUUID: String) {
        viewModelScope.launch {
            coinList.value = coinUseCase.getCoinDetail(coinUUID).data.coin
        }
    }

}