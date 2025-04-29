package com.example.eventsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.eventsapp.ui.theme.EventsAppTheme
import com.example.eventsapp.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventsAppTheme {
                AppNavGraph()
            }
        }
    }
}