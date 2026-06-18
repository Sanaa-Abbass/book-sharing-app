package com.bookshare.app.ui.theme.home


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.*

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController

import com.bookshare.app.data.model.Book
import com.bookshare.app.ui.theme.home.components.BookCard

@Composable
fun HomeScreen(
    navController: NavController
) {

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

        Text(
            text = "Hello Ahmed 👋",
            style = MaterialTheme.typography.headlineSmall
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