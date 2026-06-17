package com.bookshare.app.ui.theme.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bookshare.app.navigation.Screen


@Composable
fun RegisterScreen(
    navController: NavController
) {

    var username by remember { mutableStateOf("") }

    var email by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }

    var invitationCode by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "BookShare",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Create Account"
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
            },

            label = {
                Text("Username")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions =KeyboardOptions(keyboardType=KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = { Text("Password") },
            visualTransformation =PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions =KeyboardOptions(keyboardType =KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = invitationCode,
            onValueChange = { invitationCode = it },
            label = { Text("Invitation Code") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Will connect to API later

                if (
                    username.isBlank() ||
                    email.isBlank() ||
                    password.isBlank() ||
                    invitationCode.isBlank()
                ) {

                    return@Button
                }
            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Register"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text( text = "Already have an account? Login",

            modifier = Modifier.clickable {

                navController.navigate(
                    Screen.Login.route
                )
            }
        )
    }
}