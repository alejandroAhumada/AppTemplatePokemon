package com.template.apptemplatepokemon

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.template.apptemplatepokemon.ui.theme.PokeApiTheme
import com.template.apptemplatepokemon.view.AppNavigation
import com.template.apptemplatepokemon.view.component.TopBar
import com.template.apptemplatepokemon.view.pokemonScreen.PokemonViewModel

@Composable
fun MainCompose() {
    PokeApiTheme {
        Scaffold(
            topBar = {
                TopBar()
            }
        ){ padding ->
            val viewModel: PokemonViewModel = hiltViewModel()
            AppNavigation(viewModel, padding)
        }
    }
}