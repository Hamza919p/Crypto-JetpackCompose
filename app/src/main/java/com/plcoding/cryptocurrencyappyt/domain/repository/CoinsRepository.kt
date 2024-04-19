package com.plcoding.cryptocurrencyappyt.domain.repository

import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDetailsDto
import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDto

interface CoinsRepository {

    suspend fun getCoins() : List<CoinDto>
    suspend fun getCoinById(id: String) : CoinDetailsDto

}