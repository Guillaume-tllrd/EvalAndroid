package com.example.evalandroid.domain.pokemon

/**
 * L'objet Pokémon utilisé par toute l'application.
 * * C'est le résultat final une fois qu'on a trié les données de l'API.
 * L'UI utilise cet objet directement sans se soucier du format JSON d'origine.
 */
data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<Type>,
    val stats: Stats,

)
data class Type(
    val name: String,
    val imageUrl: String
)

data class Stats(
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val speed: Int
)