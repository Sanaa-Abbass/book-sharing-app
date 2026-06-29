package com.bookshare.app.ui.theme.home


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavController
import com.bookshare.app.data.datastore.TokenManager

import com.bookshare.app.data.model.Book
import com.bookshare.app.navigation.Screen
import com.bookshare.app.ui.theme.home.components.BookCard
import com.bookshare.app.viewmodel.AuthViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun HomeScreen(navController: NavController) {

    val context =LocalContext.current
    val authViewModel : AuthViewModel =viewModel()
    val scope =rememberCoroutineScope()

    // Load the user's profile once when the screen is first displayed.
    LaunchedEffect(Unit) {
        authViewModel.loadProfile(context)
    }
    val books = listOf(

        Book(
            1,
            "Clean Code",
            "Robert Martin",
            "Ahmed",
            true,
            "Ahmed"
        ),

        Book(
            2,
            "Atomic Habits",
            "James Clear",
            "Sara",
            true,
            "Sara"
        ),

        Book(
            3,
            "The Pragmatic Programmer",
            "Andrew Hunt",
            "Mohammed",
            true,
            "Mohammed"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = {
               // scope.launch {
                    authViewModel.logout(context){
                        navController.navigate(Screen.Login.route)
                        { popUpTo(0) }
                    }
                //}
            }
        ) {
            Text(text = "Logout")
        }

        Text(
            text = "Hello ${authViewModel.currentUser?.username ?: "Loading..."} 👋",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Email: ${authViewModel.currentUser?.email ?: ""}"
        )

        Text(
            text = "Building: ${authViewModel.currentUser?.buildingName?.ifEmpty { "Not Set" }}"
        )

        Text(
            text = if (authViewModel.currentUser?.isVerified == true)
                "Verified User ✓"
            else
                "Not Verified"
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Find books near you"
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {
                Text("Search books")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "Books Near You",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        LazyColumn {

            items(
                books
            ) { book ->

                BookCard(
                    book = book
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )
            }
        }
    }
}