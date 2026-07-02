package com.bookshare.app.ui.theme.home


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


import com.bookshare.app.navigation.Screen

import com.bookshare.app.ui.theme.home.components.BookCard
import com.bookshare.app.viewmodel.AuthViewModel
import com.bookshare.app.viewmodel.BookViewModel

import kotlinx.coroutines.launch


@Composable
fun HomeScreen(navController: NavHostController) {

    val context =LocalContext.current
    val authViewModel : AuthViewModel =viewModel()
    val bookViewModel : BookViewModel =viewModel()
    val scope =rememberCoroutineScope()

    // Load the user's profile once when the screen is first displayed.
    LaunchedEffect(Unit) {
        authViewModel.loadProfile(context)
        bookViewModel.loadBooks(context)
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(26.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Hello ${authViewModel.currentUser?.username ?: "Loading..."} 👋",
                style = MaterialTheme.typography.headlineSmall
            )

            Button(
                onClick = {
                   scope.launch {
                    authViewModel.logout(context){
                        navController.navigate(Screen.Login.route) { popUpTo(0) }
                    }
                  }
                }
            ) {
                Text(text = "Logout")
            }
        }

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

        if (bookViewModel.books.isEmpty()) {

            Text("No books available.")

        } else {

            LazyColumn {
                items(bookViewModel.books) { book ->
                    BookCard(book)
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HomeScreenPreview() {
        HomeScreen(
            navController = rememberNavController()
        )

}