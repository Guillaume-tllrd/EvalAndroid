package com.example.evalandroid.ui.features.pokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.evalandroid.R

/**
 * Écran de détail affichant les informations complètes d'un Pokémon.
 *
 * @param pokemonId L'identifiant unique du Pokémon à charger.
 * @param navController Le contrôleur permettant de gérer la navigation (ex: retour arrière).
 */
@Composable
fun DetailsScreen(
    pokemonId: Int,
    navController: NavHostController
) {
    val viewModel = viewModel { DetailsViewModel(pokemonId) }

    // J'utilise collectAsStateWithLifecycle() au lieu de collectAsState().
    // Cela permet d'arrêter la collecte des données (Flow) lorsque l'application
    // passe en arrière-plan (onStop), économisant ainsi les ressources et la batterie.
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val pokemon = uiState.pokemon
    if (uiState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    if (pokemon == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(stringResource(R.string.DetailsScreen_errorMessage))
        }
        return
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            text = pokemon.name,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))
        AsyncImage(
            model = pokemon.imageUrl,
            contentDescription = pokemon.name,
            modifier = Modifier
                .size(200.dp)
                .background(Color(0xFFF0F0F0))

        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Type",
                modifier = Modifier.padding(bottom = 10.dp)
            )
            pokemon.types.forEach { type ->

                Surface(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = type.name,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))


            Card(
                modifier = Modifier.fillMaxWidth(),

                ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Statistiques",
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    StatLine(label = "HP", value = pokemon.stats.hp)
                    StatLine(label = "Attaque", value = pokemon.stats.attack)
                    StatLine(label = "Défense", value = pokemon.stats.defense)
                    StatLine(label = "Vitesse", value = pokemon.stats.speed)
                    Spacer(modifier = Modifier.height(24.dp))

                }
            }
            Button(onClick = { navController.popBackStack() }) {
                Text("Retour")
            }

        }
    }
}
@Composable
fun StatLine(label: String, value: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 18.sp)
        Text(text = "$value", fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}
