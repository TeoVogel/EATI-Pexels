package com.eati.pexels.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eati.pexels.domain.PhotoExt


@Composable
fun PhotosScreen(viewModel: PhotosViewModel) {
    val result by viewModel.photosFlow.collectAsState()

    var searchQuery by remember { mutableStateOf("") }

    Column {
        SearchBox(input = searchQuery, onInputChange = {
            searchQuery = it
            viewModel.updateResults(searchQuery)
        })
        Photos(result)
    }
}

@Composable
fun SearchBox(input: String, onInputChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        value = input,
        label = { Text("Search for an image") },
        onValueChange = onInputChange,
        leadingIcon = { Icon(Icons.Default.Search, null) }
    )
}

@Composable
fun Photos(results: List<PhotoExt>) {

    /*LaunchedEffect(Unit) {
        updateResults("architecture")
    }*/
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(all = 8.dp),
    ) {
        items(results.size) { item ->
            /*var expanded by remember {
                mutableStateOf(false)
            }*/

            PhotoCard(
                photo = results[item],
            )
        }
    }
}