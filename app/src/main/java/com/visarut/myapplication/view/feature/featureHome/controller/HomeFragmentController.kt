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
            CoinRankBindingModel_().apply {
                id("crypto$index")
                imageUrl(coin.iconUrl)
                name(coin.name)
                symbol(coin.symbol)
                change(coin.change)
                changeFloat(coin.change.toFloat())
                onClickCoinItem { _ ->
                    this@HomeFragmentController.callback?.onClickCoin(coin.uuid)
                }
            }
        }

        val topRankSkeleton = (0..2).mapIndexed{ index, coin ->
            CoinRankSkeletonBindingModel_().apply {
                id("crypto$index")
            }
        }

        if(data?.isShowTopRank?.value == true) {
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
        }

        data?.coinList?.let {

            when{
                data.coinList.value?.isNotEmpty() == true -> {
                    headerMarket {
                        id("header_market")
                    }
                    data.coinList.value?.size?.let {
                        data.coinList.value?.subList(DEFAULT_TOP_RANK, it)?.forEach {
                            coin {
                                id(it.uuid)
                                fullName(it.name)
                                shortName(it.symbol)
                                price(String.format("%.5f", it.price.toFloat()))
                                change(it.change)
                                imageUrl(it.iconUrl)
                                changeFloat(it.change.toFloat())
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

        } ?: run {
            renderSkeleton()
        }
    }

    private fun renderSkeleton () {
        carousel {
            id("skeleton_carousel")
            for (index in 0..2) {
                CoinRankSkeletonBindingModel_().apply {
                    id("skeleton${index}")
                }
            }
        }

    }
}