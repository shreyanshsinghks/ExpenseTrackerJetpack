package com.hello.expensetrackerbyshreyansh.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hello.expensetrackerbyshreyansh.feature.add_expense.AddExpense
import com.hello.expensetrackerbyshreyansh.feature.home.HomeScreen
import com.hello.expensetrackerbyshreyansh.feature.stats.StatsScreen
import com.hello.expensetrackerbyshreyansh.ui.theme.Zinc

@Composable
fun NavHostScreen() {
    val navController = rememberNavController()
    var bottomBarVisibility by remember { mutableStateOf(true) }

    Scaffold(bottomBar = {
        AnimatedVisibility(visible = bottomBarVisibility) {
            NavigationBottomBar(
                navController, listOf(
                    NavItem("/home", Icons.Filled.Home),
                    NavItem("/stats", Icons.Filled.DateRange)
                )
            )
        }
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "/splash",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = "/home") {
                bottomBarVisibility = true
                HomeScreen(navController)
            }
            composable(route = "/add") {
                bottomBarVisibility = false
                AddExpense(navController)
            }
            composable(route = "/splash") {
                bottomBarVisibility = false
                SplashScreen(navController)
            }

            composable(route = "/stats") {
                bottomBarVisibility = true
                StatsScreen(navController)
            }
        }
    }

}


data class NavItem(
    val route: String,
    val icon: ImageVector
)

@Composable
fun NavigationBottomBar(navController: NavController, items: List<NavItem>) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    BottomAppBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Image(
                        imageVector = item.icon,
                        contentDescription = null,
                        colorFilter = if (currentRoute == item.route) ColorFilter.tint(
                            Zinc
                        ) else ColorFilter.tint(Color.Gray),
                        modifier = Modifier.size(30.dp)
                    )
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Zinc,
                    selectedTextColor = Zinc,
                    unselectedTextColor = Color.Gray,
                    unselectedIconColor = Color.Gray
                )
            )
        }
    }
}