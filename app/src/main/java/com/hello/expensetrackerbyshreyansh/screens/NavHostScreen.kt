package com.hello.expensetrackerbyshreyansh.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavHostScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "/splash") {
        composable(route = "/home"){
            HomeScreen(navController)
        }
        composable(route = "/add"){
            AddExpense(navController)
        }
        composable(route = "/splash"){
            SplashScreen(navController)
        }
    }
}