package com.example.evalandroid.domain.pokemon

/**
 * Le "contrat" pour récupérer les données.
 * * Les ViewModels utilisent cette interface pour demander des Pokémons.
 * L'avantage, c'est que le ViewModel ne sait pas d'où viennent les données .
 */
interface PokemonRepository {

    suspend fun getPokemons(): List<Pokemon>
    suspend fun getPokemonById(pokemonId: Int) : Pokemon?
}