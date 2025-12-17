package com.example.evalandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.evalandroid.ui.features.home.HomeScreen
import com.example.evalandroid.ui.features.pokemon.DetailsScreen
import com.example.evalandroid.ui.navigation.Destination
import com.example.evalandroid.ui.theme.EvalAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EvalAndroidTheme {

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Destination.Home
                ) {
                    composable<Destination.Home> {
                        HomeScreen(navController)
                    }

                    composable<Destination.PokemonDetails> { entry ->
                        val pokemonId = entry.toRoute<Destination.PokemonDetails>().pokemonId
                        DetailsScreen(
                            pokemonId = pokemonId,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

