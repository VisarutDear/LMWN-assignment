package com.visarut.myapplication.view.feature.featureHome

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visarut.myapplication.data.response.Coin
import com.visarut.myapplication.data.response.CoinDetail
import com.visarut.myapplication.domain.usecase.CoinUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val coinUseCase: CoinUseCase
) : ViewModel() {
    var coinList: MutableLiveData<List<Coin>> = MutableLiveData()
    var coinDetail: MutableLiveData<CoinDetail> = MutableLiveData()
    init {
        fetchCoin()
    }

    private fun fetchCoin() {
        viewModelScope.launch {
            coinList.value = coinUseCase.getCoin().data.coins
        }
        Log.d("test", coinList.value.toString())
    }

    fun fetchCoinDetail(coinUUID: String): CoinDetail? {
        viewModelScope.launch {
            coinDetail.value = coinUseCase.getCoinDetail(coinUUID).data.coin
        }
        return coinDetail.value
    }
}