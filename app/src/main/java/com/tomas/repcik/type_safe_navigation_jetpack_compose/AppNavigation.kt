package com.tomas.repcik.type_safe_navigation_jetpack_compose

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf


@Serializable
sealed class Routes {
    @Serializable
    data object FirstScreen : Routes()

    @Serializable
    data class SecondScreen(val screenInfo: ScreenInfo) : Routes()

}


fun NavGraphBuilder.mainGraph(navController: NavController) {
    composable<Routes.FirstScreen> {
        FirstScreen(onNavigateForward = {
            navController.navigate(
                Routes.SecondScreen(screenInfo = ScreenInfo(route = "SecondRoute", id = 1))
            )
        })
    }
    composable<Routes.SecondScreen>(
        typeMap = mapOf(
            typeOf<ScreenInfo>() to CustomNavType(
                ScreenInfo::class.java,
                ScreenInfo.serializer()
            )
        )
    ) { navBackStackEntry ->
        val parameters = navBackStackEntry.toRoute<Routes.SecondScreen>()
        Log.i("SecondScreen", parameters.toString())
        SecondScreen(onNavigateBack = {
            navController.navigate(
                Routes.FirstScreen
            )
        })
    }
}