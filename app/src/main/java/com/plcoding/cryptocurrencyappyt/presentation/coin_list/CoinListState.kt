package com.plcoding.cryptocurrencyappyt.presentation.coin_list

import com.plcoding.cryptocurrencyappyt.domain.model.Coin

data class CoinListState(
    var isLoading: Boolean = false,
    var data: List<Coin> ?= null,
    var error: String ?= null
)
