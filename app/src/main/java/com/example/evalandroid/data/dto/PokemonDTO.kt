package com.example.evalandroid.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Objet de Transfert de Données (DTO) représentant un Pokémon tel que renvoyé par l'API distante.
 *
 * @property id L'identifiant unique du Pokémon.
 * @property apiTypes Liste des types élémentaires.
 * @property stats Les statistiques de base.
 */
@Serializable
data class PokemonDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("image") val image: String,
    @SerialName("apiTypes") val apiTypes: List<ApiTypeDto> = emptyList(),
    @SerialName("stats") val stats: StatsDto? = null
)

@Serializable
data class ApiTypeDto(
    @SerialName("name") val name: String,
    @SerialName("image") val image: String
)

@Serializable
data class StatsDto(
    @SerialName("HP") val hp: Int,
    @SerialName("attack") val attack: Int,
    @SerialName("defense") val defense: Int,
    @SerialName("speed") val speed: Int
)
