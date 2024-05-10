package com.tomas.repcik.type_safe_navigation_jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.tomas.repcik.type_safe_navigation_jetpack_compose.ui.theme.TypeSafeNavigationJetpackComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TypeSafeNavigationJetpackComposeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.FirstScreen,
                ) {
                    mainGraph(navController)
                }
            }
        }
    }
}