package com.visarut.myapplication.domain.model

import androidx.lifecycle.MutableLiveData
import com.visarut.myapplication.data.response.Coin

data class CoinsData(
    var coinList : MutableLiveData<List<Coin>>
)
