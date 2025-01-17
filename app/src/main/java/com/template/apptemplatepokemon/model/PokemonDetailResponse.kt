package com.template.apptemplatepokemon.model

data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val base_experience: Int,
    val height: Int,
    val is_default: Boolean,
    val order: Int,
    val weight: Int
)