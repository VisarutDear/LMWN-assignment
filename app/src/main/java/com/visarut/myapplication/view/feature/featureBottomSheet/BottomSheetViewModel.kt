package com.visarut.myapplication.view.feature.featureBottomSheet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visarut.myapplication.data.response.AllTimeHigh
import com.visarut.myapplication.data.response.CoinDetail
import com.visarut.myapplication.data.response.Link
import com.visarut.myapplication.domain.usecase.GetCoinDetailUseCase
import com.visarut.myapplication.domain.usecase.GetCoinUseCase
import kotlinx.coroutines.launch

class BottomSheetViewModel(
    private val getCoinDetailUseCase: GetCoinDetailUseCase
) : ViewModel() {

    var coinDetail: MutableLiveData<CoinDetail> = MutableLiveData()
    var isShowWebSite = MutableLiveData(false)
    val isShowLoading = MutableLiveData(true)
    val isShowError = MutableLiveData(false)

    suspend fun fetchCoinDetail(coinUUID: String) {
        val getCoinDetailInput = GetCoinDetailUseCase.Input(coinUUID)
        getCoinDetailUseCase.execute(getCoinDetailInput)
            .onSuccess {
                coinDetail.value = it.data.coin
                setData()
                hideLoading()
                hideError()
            }
            .onFailure {
                // handle error
                handleFetchCoinDetailError(it)
                hideLoading()
                showError()
            }

    }

    private fun showError() {
        isShowError.postValue(true)
    }

    private fun hideError() {
        isShowError.postValue(false)
    }

    private fun hideLoading() {
        isShowLoading.postValue(false)
    }

    private fun handleFetchCoinDetailError(it: Throwable) {
    }

    private fun setData() {
        isShowWebSite.postValue(coinDetail.value?.websiteUrl.isNullOrBlank().not())
    }
}