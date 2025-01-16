package com.template.apptemplatepokemon.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.template.apptemplatepokemon.view.pokemonScreen.PokemonViewModel
import com.template.apptemplatepokemon.view.pokemonScreen.pokemonDetailScreen.PokemonDetailScreen
import com.template.apptemplatepokemon.view.pokemonScreen.pokemonListScreen.PokemonListScreen

@Composable
fun AppNavigation(viewModel: PokemonViewModel, padding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list", modifier = Modifier.padding(padding)) {
        composable("list") {
            PokemonListScreen(navController, viewModel)
        }
        composable("detail/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            PokemonDetailScreen(viewModel, name)
        }
    }
}