package com.example.evalandroid.data.mapper

import com.example.evalandroid.data.dto.PokemonDto
import com.example.evalandroid.domain.pokemon.Pokemon
import com.example.evalandroid.domain.pokemon.Stats
import com.example.evalandroid.domain.pokemon.Type

/**
 * Fonction qui convertit la couche Data en la couche Domain.
 *
 * Son rôle est de transformer l'objet technique [PokemonDto], qui reflète la structure JSON brute,
 * en un objet métier [Pokemon] stable et utilisable par l'interface utilisateur.
 *
 * @return Une instance immuable de [Pokemon].
 */
fun PokemonDto.toDomain(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        imageUrl = image,
        types = apiTypes.map { Type(it.name, it.image) },

        stats = Stats(
            hp = stats?.hp ?: 0,
            attack = stats?.attack ?: 0,
            defense = stats?.defense ?: 0,
            speed = stats?.speed ?: 0
        )
    )
}