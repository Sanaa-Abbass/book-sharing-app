package com.bookshare.app.ui.theme.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.bookshare.app.data.model.Book

@Composable
fun BookCard(
    book: Book
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = book.title,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = book.author
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "Owner: ${book.owner}"
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Button(
                onClick = {}
            ) {

                Text(
                    text = "Request Book"
                )
            }
        }
    }
}