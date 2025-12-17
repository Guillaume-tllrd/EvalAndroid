package com.example.evalandroid.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object Home: Destination

    @Serializable
    data class PokemonDetails(val pokemonId: Int): Destination
}