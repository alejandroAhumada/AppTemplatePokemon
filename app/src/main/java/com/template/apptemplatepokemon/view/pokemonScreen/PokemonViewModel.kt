package com.template.apptemplatepokemon.view.pokemonScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.apptemplatepokemon.data.PokemonRepository
import com.template.apptemplatepokemon.model.PokemonDetailResponse
import com.template.apptemplatepokemon.view.modelUi.PokemonModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {
    private val _pokemonDetail = MutableStateFlow<PokemonDetailResponse?>(null)
    val pokemonDetail: StateFlow<PokemonDetailResponse?> = _pokemonDetail

    val pokemonList: StateFlow<List<PokemonModel>> = repository.getPokemonFromDb()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun fetchPokemonList() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchPokemonList(1400)
        }
    }

    fun fetchPokemonDetail(name: String) {
        viewModelScope.launch {
            _pokemonDetail.value = repository.getPokemonDetail(name)
        }
    }
}