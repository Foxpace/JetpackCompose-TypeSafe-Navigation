package com.tomas.repcik.type_safe_navigation_jetpack_compose

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
@Parcelize
data class ScreenInfo(val route: String, val id: Int) : Parcelable

class CustomNavType<T : Parcelable>(
    private val clazz: Class<T>,
    private val serializer: KSerializer<T>,
) : NavType<T>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): T? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, clazz) as T
        } else {
            @Suppress("DEPRECATION") // for backwards compatibility
            bundle.getParcelable(key)
        }

    override fun parseValue(value: String): T = Json.decodeFromString(serializer, value)

    override fun put(bundle: Bundle, key: String, value: T) =
        bundle.putParcelable(key, value)

    override fun serializeAsValue(value: T): String = Json.encodeToString(serializer, value)

    override val name: String = clazz.name
}

// As separated object
// val ScreenInfoNavType = object : NavType<ScreenInfo>(isNullableAllowed = false) {
//    override fun get(bundle: Bundle, key: String): ScreenInfo? =
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            bundle.getParcelable(key, ScreenInfo::class.java) as ScreenInfo
//        } else {
//            @Suppress("DEPRECATION") // for backwards compatibility
//            bundle.getParcelable(key)
//        }
//
//    override fun parseValue(value: String): ScreenInfo = Json.decodeFromString<ScreenInfo>(value)
//
//    override fun put(bundle: Bundle, key: String, value: ScreenInfo) =
//        bundle.putParcelable(key, value)
//
//    override fun serializeAsValue(value: ScreenInfo): String = Json.encodeToString(value)
//
//    override val name: String = "ScreenInfo"
//
//}