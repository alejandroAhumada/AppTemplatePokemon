package com.template.apptemplatepokemon.model

import com.template.apptemplatepokemon.data.database.PokemonEntity

data class PokemonResult(
    val name: String,
    val url: String
){
    fun toPokemonEntity(): PokemonEntity {
        return PokemonEntity(
            name, url
        )
    }
}