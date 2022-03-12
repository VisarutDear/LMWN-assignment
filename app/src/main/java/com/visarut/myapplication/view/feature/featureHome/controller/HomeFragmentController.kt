package com.visarut.myapplication.view.feature.featureHome.controller

import android.util.Log
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.epoxy.carousel
import com.visarut.myapplication.*
import com.visarut.myapplication.domain.model.CoinsData
import com.visarut.myapplication.view.feature.featureHome.HomeViewModel

class HomeFragmentController : TypedEpoxyController<CoinsData>() {

    companion object {
        const val DEFAULT_TOP_RANK = 3
    }

    private var callback: HomeFragmentController.AddOnItemSelected? = null

    interface AddOnItemSelected {
        fun onClickCoin(uuid : String)
    }

    fun setItemSelectListener(callback : AddOnItemSelected){
        this.callback = callback
    }

    override fun buildModels(data: CoinsData?) {

        Log.d("test", data?.coinList?.value.toString())

        val topRank = data?.coinList?.value?.take(DEFAULT_TOP_RANK)?.mapIndexed{ index, coin ->
            CryptoRankBindingModel_().apply {
                id("crypto$index")
                imageUrl(coin.iconUrl)
                name(coin.name)
                symbol(coin.symbol)
                change(coin.change)
                onClickCoinItem { _ ->
                    this@HomeFragmentController.callback?.onClickCoin(coin.uuid)
                }
            }
        }

        searchFilter {
            id("search_filter")
        }

        headerTopRank {
            id("header_top_rank")
        }

        carousel {
            id("top_rank_carousel")
            topRank?.let {
                models(
                    it
                )
            }
        }

        headerMarket {
            id("header_market")
        }


//        if(HomeViewModel.isLoadMore) data?.coinList?.postValue(null)
        when{
            data == null -> {
                // render skeleton
            }
            data.coinList.value?.isNotEmpty() == true -> {
                data.coinList.value?.size?.let {
                    data.coinList.value?.subList(DEFAULT_TOP_RANK, it)?.forEach {
                        coin {
                            id(it.uuid)
                            fullName(it.name)
                            shortName(it.symbol)
                            price(it.price)
                            percentage(it.change)
                            imageUrl(it.iconUrl)
                            onClickCoinItem { _ ->
                                this@HomeFragmentController.callback?.onClickCoin(it.uuid)
                            }
                        }
                    }
                } ?: run {
                    loadMore {
                        id("load_more")
                    }
                }
            }

        }

    }
}