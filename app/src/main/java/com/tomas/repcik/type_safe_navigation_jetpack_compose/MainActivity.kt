package com.tomas.repcik.type_safe_navigation_jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.tomas.repcik.type_safe_navigation_jetpack_compose.ui.theme.TypeSafeNavigationJetpackComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TypeSafeNavigationJetpackComposeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "/"
                ) {
                    mainGraph(navController)
                }
            }
        }
    }

    private fun NavGraphBuilder.mainGraph(navController: NavController) {
        navigation(startDestination = "/firstScreen", route = "/") {
            composable("/firstScreen") { FirstScreen(onNavigateForward = { navController.navigate("/secondScreen") }) }
            composable("/secondScreen") { SecondScreen(onNavigateBack = { navController.navigate("/firstScreen") }) }
        }
    }

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
}