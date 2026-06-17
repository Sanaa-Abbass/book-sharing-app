package com.bookshare.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bookshare.app.ui.theme.home.HomeScreen
import com.bookshare.app.ui.theme.login.LoginScreen
import com.bookshare.app.ui.theme.register.RegisterScreen
import com.bookshare.app.ui.theme.splash.SplashScreen


@Composable
fun AppNavigation() {

    val  navController = rememberNavController()
    NavHost(navController =  navController , startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable(Screen.Login.route) {
            LoginScreen(navController)
        }

        composable(Screen.Register.route) {
            RegisterScreen(navController)
        }

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

    }
}