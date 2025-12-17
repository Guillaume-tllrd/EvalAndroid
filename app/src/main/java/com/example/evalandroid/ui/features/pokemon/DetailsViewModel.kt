package com.example.evalandroid.ui.features.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evalandroid.domain.pokemon.Pokemon
import com.example.evalandroid.domain.pokemon.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Contrat d'interface définissant l'état (State) de l'écran de détails.
 *
 */
interface PokemonDetailsContracts {
    data class UiState(
        val pokemon: Pokemon? = null,
        val isLoading: Boolean = true,
        val errorMessage: String? = null
    )
}
/**
 * ViewModel dédié à la logique métier de l'écran de détails.
 *
 * Particularité architecturale :
 * Ce ViewModel reçoit un paramètre d'exécution dynamique [pokemonId] via son constructeur.
 * Il implémente l'interface [KoinComponent], ce qui lui permet de récupérer
 * ses dépendances (Repository)
 *
 * @param pokemonId L'identifiant unique du Pokémon demandé par la vue.
 */
class DetailsViewModel(
    private val pokemonId: Int
) :  ViewModel(), KoinComponent {
    private val pokemonRepository: PokemonRepository by inject()

    private var _state = MutableStateFlow(PokemonDetailsContracts.UiState())
    val state : StateFlow<PokemonDetailsContracts.UiState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO){
            val result = pokemonRepository.getPokemonById(pokemonId)
            _state.update{
                it.copy(
                    pokemon = result,
                    isLoading = false
                )
            }
        }
    }

}