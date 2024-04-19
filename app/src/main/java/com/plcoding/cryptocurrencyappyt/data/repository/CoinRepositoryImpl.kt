package com.plcoding.cryptocurrencyappyt.data.repository

import com.plcoding.cryptocurrencyappyt.data.remote.CoinPaprikaApi
import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDetailsDto
import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDto
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinsRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinPaprikaApi: CoinPaprikaApi
) : CoinsRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return coinPaprikaApi.getCoins()
    }

    override suspend fun getCoinById(id: String): CoinDetailsDto {
        return coinPaprikaApi.getCoinById(id)
    }

}