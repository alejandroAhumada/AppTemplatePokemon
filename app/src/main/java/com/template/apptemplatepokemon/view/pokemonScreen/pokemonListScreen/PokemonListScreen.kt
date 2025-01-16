package com.template.apptemplatepokemon.view.pokemonScreen.pokemonListScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.template.apptemplatepokemon.view.modelUi.PokemonModel
import com.template.apptemplatepokemon.view.pokemonScreen.PokemonViewModel

@Composable
fun PokemonListScreen(navController: NavController, viewModel: PokemonViewModel) {
    val pokemonList by viewModel.pokemonList.collectAsState()
    val searchQuery = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.fetchPokemonList()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ItemSearchScreen(searchQuery)
        LazyColumn {
            items(pokemonList) { pokemon ->
                if (searchQuery.value.isNotEmpty()) {
                    if (pokemon.name.contains(
                            searchQuery.value,
                            ignoreCase = true
                        )
                    ){
                        PokemonItem(navController, pokemon)
                    }
                }else {
                    PokemonItem(navController, pokemon)
                }
            }
        }
    }
}

@Composable
fun PokemonItem(navController: NavController, pokemon: PokemonModel) {
    ListItem({
        Column(modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("detail/${pokemon.name}") }) {
            Row {
                Column {
                    Text(
                        text = pokemon.name.uppercase() ?: "No Title",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            Divider(
                thickness = 2.dp,
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
            )
        }
    })
}

@Composable
fun ItemSearchScreen(searchQuery: MutableState<String>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = searchQuery.value,
            onValueChange = { searchQuery.value = it },
            label = { Text("Enter Item") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}