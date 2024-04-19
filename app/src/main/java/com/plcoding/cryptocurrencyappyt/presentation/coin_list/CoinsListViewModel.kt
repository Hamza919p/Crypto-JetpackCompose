package com.plcoding.cryptocurrencyappyt.presentation.coin_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.domain.model.Coin
import com.plcoding.cryptocurrencyappyt.domain.use_cases.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinsListViewModel @Inject constructor (
    val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _coinsListState = mutableStateOf(CoinListState())
    val coinsListState = _coinsListState

    init {
        getCoinsList()
    }

    private fun getCoinsList() : List<Coin> {
        //as we have use 'invoke' we can calling class like function
        getCoinsUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _coinsListState.value = CoinListState(
                        isLoading = false,
                        data = result.data,
                        error = null
                    )
                }

                is Resource.Error -> {
                    _coinsListState.value = CoinListState(
                        isLoading = false,
                        error = result.message ?: "An error occurred"
                    )
                }

                is Resource.Loading -> {
                    _coinsListState.value = CoinListState(isLoading = true)
                }
            }
        }
    }

}