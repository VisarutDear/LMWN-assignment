package com.visarut.myapplication.data.response

data class CoinDetailResponse(
    val `data`: CoinDetailData,
    val status: String
)

data class CoinDetailData(
    val coin: CoinDetail
)

data class CoinDetail(
    val `24hVolume`: String,
    val allTimeHigh: AllTimeHigh,
    val btcPrice: String,
    val change: String,
    val coinrankingUrl: String,
    val color: String,
    val description: String?,
    val iconUrl: String,
    val links: List<Link>,
    val listedAt: Int,
    val lowVolume: Boolean,
    val marketCap: String,
    val name: String,
    val numberOfExchanges: Int,
    val numberOfMarkets: Int,
    val price: String,
    val priceAt: Int,
    val rank: Int,
    val sparkline: List<String>,
    val supply: Supply,
    val symbol: String,
    val tier: Int,
    val uuid: String,
    val websiteUrl: String?
)

data class Link(
    val name: String,
    val type: String,
    val url: String
)

data class Supply(
    val circulating: String,
    val confirmed: Boolean,
    val total: String
)

data class AllTimeHigh(
    val price: String,
    val timestamp: Int
)