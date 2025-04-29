package com.example.eventsapp.data

data class Event(
    val id: String,
    val title: String,
    val date: String,
    val time: String,
    val location: String,
    var isRegistered: Boolean = false
)