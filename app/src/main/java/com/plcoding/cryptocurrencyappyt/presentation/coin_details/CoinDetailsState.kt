package com.plcoding.cryptocurrencyappyt.presentation.coin_details

import com.plcoding.cryptocurrencyappyt.domain.model.CoinDetails

data class CoinDetailsState(
    var isLoading: Boolean = false,
    var data: CoinDetails ?= null,
    var error: String ?= null
)
