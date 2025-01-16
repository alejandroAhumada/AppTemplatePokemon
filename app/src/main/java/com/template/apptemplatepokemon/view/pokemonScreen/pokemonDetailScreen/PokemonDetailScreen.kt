package com.template.apptemplatepokemon.view.pokemonScreen.pokemonDetailScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.template.apptemplatepokemon.view.pokemonScreen.PokemonViewModel

@Composable
fun PokemonDetailScreen(viewModel: PokemonViewModel, name: String) {
    val pokemonDetail by viewModel.pokemonDetail.collectAsState()

    LaunchedEffect(name) {
        viewModel.fetchPokemonDetail(name)
    }

    pokemonDetail?.let {
        Column {
            Text("ID: ${it.id}")
            Text("Name: ${it.name}")
            Text("Base Experience: ${it.base_experience}")
            Text("Height: ${it.height}")
            Text("Weight: ${it.weight}")
        }
    }
}