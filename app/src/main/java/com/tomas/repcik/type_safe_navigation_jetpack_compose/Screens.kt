package com.tomas.repcik.type_safe_navigation_jetpack_compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FirstScreen(onNavigateForward: () -> Unit) {
    SimpleScreen(text = "First Screen", textButton = "Go forward") {
        onNavigateForward()
    }
}

// Define the FriendsList composable.
@Composable
fun SecondScreen(onNavigateBack: () -> Unit) {
    SimpleScreen(text = "Second Screen", textButton = "Go back") {
        onNavigateBack()
    }
}

@Composable
fun SimpleScreen(text: String, textButton: String, onClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text)
        Button(onClick = onClick) {
            Text(textButton)
        }
    }
}