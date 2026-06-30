package com.bookshare.app.ui.theme.login

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bookshare.app.navigation.Screen
import com.bookshare.app.viewmodel.AuthViewModel
import androidx.compose.ui.platform.LocalContext

@Composable
fun LoginScreen(navController: NavHostController) {

    var  username  by remember { mutableStateOf("") }
    var  password  by remember { mutableStateOf("") }
    val viewModel : AuthViewModel = viewModel()
    val context = LocalContext.current



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "BookShare",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = ({username = it}),
            label = { Text("Username")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },

            visualTransformation =PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                // Temporary navigation
                //navController.navigate(Screen.Home.route)
                viewModel.login(context,username , password){
                    navController.navigate(Screen.Home.route)
                }
            },

        ) {

            Text(
                text = "Login"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Forgot Password?"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                navController.navigate(
                    Screen.Register.route
                )
            }
        ) {

            Text(
                text = "Register"
            )
        }
    }
}