package com.plcoding.cryptocurrencyappyt.presentation.coin_details

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Constraints
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.common.Constants
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.domain.use_cases.get_coin_details.GetCoinDetailsUseCase
import com.plcoding.cryptocurrencyappyt.domain.use_cases.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinsDetailsViewModel @Inject constructor (
    val getCoinDetailsUseCase: GetCoinDetailsUseCase,
    savedStateHandle: SavedStateHandle //this will be passed from our previous screen as a bundle.
    //it is a special kind.
) : ViewModel() {

    private val _coinDetailsState = mutableStateOf(CoinDetailsState())
    val coinsListState = _coinDetailsState

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { id ->
            getCoinDetail(id)
        }
    }

    private fun getCoinDetail(id: String) {
        //as we have use 'invoke' we can calling class like function
        getCoinDetailsUseCase(id).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _coinDetailsState.value = CoinDetailsState(
                        isLoading = false,
                        data = result.data,
                        error = null
                    )
                }

                is Resource.Error -> {
                    _coinDetailsState.value = CoinDetailsState(
                        isLoading = false,
                        error = result.message ?: "An error occurred"
                    )
                }

                is Resource.Loading -> {
                    _coinDetailsState.value = CoinDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}