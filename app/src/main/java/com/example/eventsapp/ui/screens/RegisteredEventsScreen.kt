package com.example.eventsapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eventsapp.data.generateDummyEvents
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisteredEventsScreen(navController: NavController) {
    val allEvents = remember { generateDummyEvents() }
    val registeredEvents = allEvents.filter { it.isRegistered }

    Scaffold(
        topBar = { SmallTopAppBar(title = { Text("Registered Events") }) }
    ) { inner ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
        ) {
            items(registeredEvents) { event ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { navController.navigate("detail/${event.id}") }
                ) {
                    Column(
                        Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(event.date, fontWeight = FontWeight.Bold)
                        Text(event.title)
                        Text(event.location, style = MaterialTheme.typography.bodySmall)
                        Spacer(Modifier.height(8.dp))
                        Button(
                            onClick = { /* cancel action */ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF5350))
                        ) {
                            Text("Cancel Event")
                        }
                    }
                }
            }
        }
    }
}
