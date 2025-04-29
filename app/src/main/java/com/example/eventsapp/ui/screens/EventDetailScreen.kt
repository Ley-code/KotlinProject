package com.example.eventsapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eventsapp.data.generateDummyEvents
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailScreen(navController: NavController, eventId: String) {
    val event = remember { generateDummyEvents().find { it.id == eventId } }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(event?.title ?: "Detail") },
                navigationIcon = { IconButton(onClick = { navController.popBackStack() }) { Icon(
                    Icons.Default.ArrowBack, null) } }
            )
        }
    ) { inner ->
        event?.let {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(inner)
                    .padding(16.dp)
            ) {
                // Info card
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(16.dp)) {
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Date:", fontWeight = FontWeight.Medium)
                            Text(it.date)
                        }
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Time:", fontWeight = FontWeight.Medium)
                            Text(it.time)
                        }
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Location:", fontWeight = FontWeight.Medium)
                            Text(it.location)
                        }
                    }
                }
                Spacer(Modifier.height(16.dp))
                // Description card
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Description", fontWeight = FontWeight.SemiBold)
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua..."
                        )
                    }
                }
                Spacer(Modifier.weight(1f))
                // Action button
                Button(
                    onClick = { /* toggle register/cancel */ },
                    colors = if (!it.isRegistered) ButtonDefaults.buttonColors() else ButtonDefaults.buttonColors(containerColor = Color(0xFFEF5350)),
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                ) {
                    Text(if (!it.isRegistered) "Register" else "Cancel Event")
                }
            }
        } ?: Text("Event not found", Modifier.padding(16.dp))
    }
}
