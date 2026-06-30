package com.bookshare.app.ui.theme.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.bookshare.app.data.datastore.TokenManager
import com.bookshare.app.navigation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first

@Composable
fun SplashScreen(navController: NavHostController) {
    val context =LocalContext.current

    LaunchedEffect(Unit){
        val token = TokenManager(context).accessToken.first()

        if (token != null){
            navController.navigate(Screen.Home.route){
                popUpTo(Screen.Splash.route){
                    inclusive = true
                }
            }
        }else{
            navController.navigate(Screen.Login.route){
                popUpTo(Screen.Splash.route){
                    inclusive = true
                }
            }
        }
    }

    Box(
        Modifier.fillMaxSize(), Alignment.Center
    ){
        Text(  text = "BookShare")

    }
}