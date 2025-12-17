package com.example.evalandroid.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evalandroid.domain.pokemon.Pokemon
import com.example.evalandroid.domain.pokemon.PokemonRepository
import com.example.evalandroid.system.SoundManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

/**
 * Contrat d'interface définissant l'état de l'écran d'accueil.
 *
 */
interface HomeContracts {
    data class UiState(
        val pokemons: List<Pokemon> = emptyList(),
        val isLoading: Boolean = true,
        val errorMessage: String? = null,
    )
}

/**
 * ViewModel responsable de la logique métier de l'écran d'accueil.
 *
 * Il a pour rôles :
 * 1. Exposer un état réactif ([StateFlow]) à la vue.
 * 2. Gérer les appels asynchrones vers le repository.
 * 3. Réagir aux intentions de l'utilisateur (clics).
 *
 * Implémente [KoinComponent] pour permettre l'injection de dépendances.
 */
class HomeViewModel : ViewModel(), KoinComponent {
    private val pokemonsRepository: PokemonRepository by inject()

    private val soundManager: SoundManager by inject()
    private var _state = MutableStateFlow(HomeContracts.UiState())
    val state: StateFlow<HomeContracts.UiState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {

            val result = pokemonsRepository.getPokemons()
            _state.update {
                it.copy(
                    pokemons = result,
                    isLoading = false
                )
            }
        }
    }

    /**
     * Gère l'événement "Clic sur un Pokémon".
     *
     * Délègue l'effet sonore au [SoundManager] injecté
     */
    fun onPokemonClicked() {
        soundManager.playClickSound()
    }
}