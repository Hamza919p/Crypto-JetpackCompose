package com.plcoding.cryptocurrencyappyt.domain.use_cases.get_coin_details

import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoin
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoinDetails
import com.plcoding.cryptocurrencyappyt.domain.model.Coin
import com.plcoding.cryptocurrencyappyt.domain.model.CoinDetails
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

class GetCoinDetailsUseCase @Inject constructor(
    val repository: CoinsRepository
) {

    //we use operate invoke because as a single use case has only only functionality
    //so we are representing GetCoinsUseCase class as a function
    operator fun invoke(id: String) : Flow<Resource<CoinDetails>> = flow {
        try {

            val coin = repository.getCoinById(id).toCoinDetails()
            emit(Resource.Success(coin))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error occured"))
        } catch (e: Exception) {
            emit(Resource.Error("Internet connection error"))
        }
    }

}