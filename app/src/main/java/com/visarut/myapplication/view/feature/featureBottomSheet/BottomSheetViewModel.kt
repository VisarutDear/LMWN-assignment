package com.visarut.myapplication.view.feature.featureBottomSheet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visarut.myapplication.data.response.CoinDetail
import com.visarut.myapplication.domain.usecase.CoinUseCase
import kotlinx.coroutines.launch

class BottomSheetViewModel(
    private val coinUseCase: CoinUseCase
) : ViewModel() {

    var coinDetail: MutableLiveData<CoinDetail> = MutableLiveData()
    var isShowWebSite = MutableLiveData(false)

    fun fetchCoinDetail(coinUUID: String) {
        viewModelScope.launch {
            coinDetail.value = coinUseCase.getCoinDetail(coinUUID).data.coin
        }
        setData()
    }

    private fun setData() {
        isShowWebSite.postValue(coinDetail.value?.websiteUrl.isNullOrBlank().not())
    }

}