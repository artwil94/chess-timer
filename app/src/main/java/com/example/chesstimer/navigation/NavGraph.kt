package com.example.chesstimer.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.chesstimer.home.HomeScreen

@ExperimentalMaterial3Api
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: Screen = Screen.Home
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable<Screen.Home> {
            HomeScreen()
        }
    }

}