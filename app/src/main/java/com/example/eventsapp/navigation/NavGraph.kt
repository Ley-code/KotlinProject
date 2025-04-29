package com.example.eventsapp.navigation


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eventsapp.ui.screens.EventDetailScreen
import com.example.eventsapp.ui.screens.RegisteredEventsScreen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import com.example.eventsapp.ui.screens.EventsListScreen


sealed class BottomNavItem(val route: String, val icon: ImageVector) {
    object Events : BottomNavItem("events_list", Icons.Default.Email)
    object Registered : BottomNavItem("registered", Icons.Default.CheckCircle)
    // add other tabs if needed
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            val items = listOf(
                BottomNavItem.Events,
                BottomNavItem.Registered
            )
            NavigationBar {
                val current = navController.currentBackStackEntryAsState().value?.destination?.route
                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = null) },
                        selected = current == item.route,
                        onClick = { navController.navigate(item.route) { launchSingleTop = true } }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Events.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Events.route) { EventsListScreen(navController) }
            composable(BottomNavItem.Registered.route) { RegisteredEventsScreen(navController) }
            composable("detail/{eventId}") { backStack ->
                val id = backStack.arguments?.getString("eventId")!!
                EventDetailScreen(navController, eventId = id)
            }
        }
    }
}
