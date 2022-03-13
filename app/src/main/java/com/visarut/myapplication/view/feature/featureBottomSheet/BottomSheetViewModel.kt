package com.visarut.myapplication.view.feature.featureBottomSheet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visarut.myapplication.data.response.CoinDetail
import com.visarut.myapplication.domain.usecase.GetCoinDetailUseCase
import com.visarut.myapplication.domain.usecase.GetCoinUseCase
import kotlinx.coroutines.launch

class BottomSheetViewModel(
    private val getCoinDetailUseCase: GetCoinDetailUseCase
) : ViewModel() {

    var coinDetail: MutableLiveData<CoinDetail> = MutableLiveData()
    var isShowWebSite = MutableLiveData(false)

    fun fetchCoinDetail(coinUUID: String) {
        viewModelScope.launch {
            val getCoinDetailInput = GetCoinDetailUseCase.Input(coinUUID)

            getCoinDetailUseCase.execute(getCoinDetailInput)
                .onSuccess {
                    coinDetail.value = it.data.coin
                }
                .onFailure {
                    // handle error
                    handleFetchCoinDetailError(it)
                }

        }
        setData()
    }

    private fun handleFetchCoinDetailError(it: Throwable) {
        TODO("Not yet implemented")
    }

    private fun setData() {
        isShowWebSite.postValue(coinDetail.value?.websiteUrl.isNullOrBlank().not())
    }

}