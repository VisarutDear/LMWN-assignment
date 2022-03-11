package com.visarut.myapplication.view.feature.featureHome.controller

import android.util.Log
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.epoxy.carousel
import com.visarut.myapplication.*
import com.visarut.myapplication.domain.model.CoinsData

class HomeFragmentController() : TypedEpoxyController<CoinsData>() {

    private var callback: HomeFragmentController.AddOnItemSelected? = null

    interface AddOnItemSelected {
        fun onClickCoin(uuid : String)
    }

    fun setItemSelectListener(callback : AddOnItemSelected){
        this.callback = callback
    }

    override fun buildModels(data: CoinsData?) {

        Log.d("test", data?.coinList?.value.toString())

        val topRank = listOf(1,2,3).mapIndexed{ index, i ->
            CryptoRankBindingModel_().apply {
                id("crypto$index")
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
            topRank.let {
                models(
                    it
                )
            }
        }

        headerMarket {
            id("header_market")
        }

        data?.coinList?.value?.forEach {
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




    }
}