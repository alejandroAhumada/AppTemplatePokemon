package com.template.apptemplatepokemon.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.template.apptemplatepokemon.view.modelUi.PokemonModel

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey val name: String,
    val url: String
) {
    fun toPokemonModel(): PokemonModel{
        return PokemonModel(
            name, url
        )
    }
}