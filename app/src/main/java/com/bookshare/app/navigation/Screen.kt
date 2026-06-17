package com.bookshare.app.navigation


sealed class Screen(val  route: String){
    object Splash: Screen("splash")
    object Login: Screen("login")
    object Register: Screen("register")
    object Home: Screen("home")
   // object ProfileScreen: Screen("profile")
}