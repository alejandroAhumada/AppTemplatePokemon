package com.template.apptemplatepokemon.data

import com.template.apptemplatepokemon.data.database.PokemonDao
import com.template.apptemplatepokemon.model.PokemonDetailResponse
import com.template.apptemplatepokemon.view.modelUi.PokemonModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepository @Inject constructor(
    private val api: PokeService,
    private val dao: PokemonDao
) {
    suspend fun fetchPokemonList(limit: Int) {
        val response = api.getPokemonList(limit)
        dao.insertAllPokemon(response.results.map { it.toPokemonEntity() })
    }

    suspend fun getPokemonDetail(name: String): PokemonDetailResponse {
        return api.getPokemonDetail(name)
    }

    fun getPokemonFromDb(): Flow<List<PokemonModel>> {
        val map = dao.getAllPokemon().map {
            it.map { it.toPokemonModel() }
        }

        return map
    }
}