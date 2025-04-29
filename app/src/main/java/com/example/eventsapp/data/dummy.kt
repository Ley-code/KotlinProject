package com.example.eventsapp.data

fun generateDummyEvents(): List<Event> {
    return listOf(
        Event("1", "Music Concert", "2025-05-01", "7:00 PM", "City Hall"),
        Event("2", "Tech Meetup", "2025-05-03", "5:00 PM", "Tech Park"),
        Event("3", "Art Exhibition", "2025-05-05", "3:00 PM", "Art Center")
    )
}
