package com.example.eventsapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eventsapp.data.generateDummyEvents
import com.example.eventsapp.data.Event
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsListScreen(navController: NavController) {
    var search by remember { mutableStateOf("") }
    val allEvents = remember { generateDummyEvents() }
    var showRegistered by remember { mutableStateOf(false) }
    val filtered = allEvents.filter {
        it.title.contains(search, ignoreCase = true) && it.isRegistered == showRegistered
    }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Events") },
                actions = {
                    OutlinedTextField(
                        value = search,
                        onValueChange = { search = it },
                        placeholder = { Text("Search events") },
                        leadingIcon = { Icon(Icons.Default.Search, null) },
                        trailingIcon = { IconButton(onClick = { /* filter action */ }) { Icon(Icons.Default.Menu, null) } },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(4.dp),
                        singleLine = true
                    )
                }
            )
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
        ) {
            TabRow(selectedTabIndex = if (showRegistered) 1 else 0) {
                Tab(text = { Text("Unregistered") }, selected = !showRegistered, onClick = { showRegistered = false })
                Tab(text = { Text("Registered") }, selected = showRegistered, onClick = { showRegistered = true })
            }
            Spacer(Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(filtered) { event ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { navController.navigate("detail/${event.id}") }
                    ) {
                        Row(
                            Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .background(Color(0xFFEEEEEE), shape = MaterialTheme.shapes.small),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(event.date.take(3), fontWeight = FontWeight.Bold)
                                        Text(event.date.substring(4), fontWeight = FontWeight.Bold)
                                    }
                                }
                                Spacer(Modifier.width(16.dp))
                                Column {
                                    Text(event.title, fontWeight = FontWeight.SemiBold)
                                    Text(event.location, style = MaterialTheme.typography.bodySmall)
                                }
                            }
                            Button(onClick = { /* register action */ }, enabled = !event.isRegistered) {
                                Text(if (event.isRegistered) "Registered" else "Register")
                            }
                        }
                    }
                }
            }
        }
    }
}
