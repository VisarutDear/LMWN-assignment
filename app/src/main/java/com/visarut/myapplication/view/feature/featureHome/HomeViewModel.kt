package com.visarut.myapplication.view.feature.featureHomeimport androidx.lifecycle.MutableLiveDataimport androidx.lifecycle.ViewModelimport androidx.lifecycle.viewModelScopeimport com.visarut.myapplication.data.response.Coinimport com.visarut.myapplication.data.response.Statsimport com.visarut.myapplication.domain.usecase.GetCoinUseCaseimport com.visarut.myapplication.domain.usecase.SearchCoinUseCaseimport kotlinx.coroutines.Jobimport kotlinx.coroutines.delayimport kotlinx.coroutines.launchclass HomeViewModel(    private val getCoinUseCase: GetCoinUseCase,    private val searchCoinUseCase: SearchCoinUseCase) : ViewModel() {    val coinList: MutableLiveData<List<Coin>> = MutableLiveData()    private val coinStat: MutableLiveData<Stats> = MutableLiveData()    val isShowTopRank = MutableLiveData(true)    val isShowSkeleton = MutableLiveData(true)    val isShowRetry = MutableLiveData(false)    val isShowSearchError = MutableLiveData(false)    val isSearching = MutableLiveData(false)    private val errorMessage = MutableLiveData("")    private var searchJob: Job? = null    companion object {        const val DEFAULT_OFFSET = 0        const val DEFAULT_LIMIT = 20        const val DEBOUNCE_SEARCH = 500L        var isLoadMore = true    }    init {        fetchCoin()    }    fun fetchCoin() {        viewModelScope.launch {            val getCoinInput = GetCoinUseCase.Input(DEFAULT_OFFSET, DEFAULT_LIMIT)            getCoinUseCase.execute(getCoinInput)                .onSuccess {                    coinList.value = it.data.coins                    coinStat.value = it.data.stats                }                .onFailure {                    // handle error                    handleFetchCoinError(it)                    showRetryButton()                }            hideSkeleton()        }    }    fun isLoadMore(): Boolean {        val currentListSize = coinList.value?.size ?: 0        isLoadMore = currentListSize < coinStat.value?.total ?: 0        return isLoadMore    }    fun fetchMoreCoin() {        val currentListSize = coinList.value?.size ?: 0        viewModelScope.launch {            val getCoinInput = GetCoinUseCase.Input(currentListSize, DEFAULT_LIMIT)            getCoinUseCase.execute(getCoinInput)                .onSuccess {                    val response = it.data.coins                    var tempObj = coinList.value                    val oldList = tempObj?.toMutableList()                    oldList?.addAll(response)                    tempObj = oldList                    tempObj.apply {                        coinList.value = this//                        isLoadMore()                    }                }                .onFailure {                    // handle load more error                    handleLoadMoreError(it)                }        }    }    private fun handleLoadMoreError(throwable: Throwable) {    }    fun searchCoin(query: String) {        hideAllError()        if(query.isNotEmpty()) {            viewModelScope.launch {                val searchCoinInput = SearchCoinUseCase.Input(query)                searchCoinUseCase.execute(searchCoinInput)                    .onSuccess {                        hideSearching()                        coinList.value = it.data.coins                        coinStat.value = it.data.stats                        if(it.data.coins.isEmpty()) {                            showSearchError()                        }                    }                    .onFailure {                        // handle search coin error                        handleSearchCoinError(it)                        hideSearching()                        showSearchError()                    }            }        } else {            fetchCoin()        }    }    fun hideAllError() {        hideRetryButton()        hideSearchError()        hideSkeleton()        hideSearching()    }    private fun handleSearchCoinError(throwable: Throwable) {        when (throwable.localizedMessage) {            "timeout" -> errorMessage.postValue("Time out")            else -> errorMessage.postValue("Error")        }    }    fun hideTopRank() {        isShowTopRank.postValue(false)    }    fun showTopRank() {        isShowTopRank.postValue(true)    }    fun showSkeleton() {        isShowSkeleton.postValue(true)    }    private fun hideSkeleton() {        isShowSkeleton.postValue(false)    }    private fun showRetryButton() {        isShowRetry.postValue(true)    }    private fun hideRetryButton() {        isShowRetry.postValue(false)    }    private fun showSearchError() {        isShowSearchError.postValue(true)    }    private fun hideSearchError() {        isShowSearchError.postValue(false)    }    fun showSearching() {        isSearching.postValue(true)    }    private fun hideSearching() {        isSearching.postValue(false)    }    private fun handleFetchCoinError(throwable: Throwable) {        when (throwable.localizedMessage) {            "timeout" -> errorMessage.postValue("Time out")            else -> errorMessage.postValue("Error")        }    }}