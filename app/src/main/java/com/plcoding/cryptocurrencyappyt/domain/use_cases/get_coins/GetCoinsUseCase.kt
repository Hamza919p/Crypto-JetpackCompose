package com.plcoding.cryptocurrencyappyt.domain.use_cases.get_coins

import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoin
import com.plcoding.cryptocurrencyappyt.domain.model.Coin
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    val repository: CoinsRepository
) {

    //we use operate invoke because as a single use case has only only functionality
    //so we are representing GetCoinsUseCase class as a function
    operator fun invoke() : Flow<Resource<List<Coin>>> = flow {
        try {

            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error occured"))
        } catch (e: Exception) {
            emit(Resource.Error("Internet connection error"))
        }
    }

}