package ru.sogya.avito.avito_test_task_trainee.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import ru.sogya.avito.avito_test_task_trainee.core.uikit.component.MovieExpandedItem
import ru.sogya.avito.avito_test_task_trainee.core.uikit.component.itemsPaging

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeVM = hiltViewModel<HomeVM>()
) {
    val states = viewModel.uiState.collectAsState()
    val movies = states.value.movies.collectAsLazyPagingItems()
    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = states.value.searchQuery,
            onQueryChange = { queary ->
                viewModel.handleIntents(HomeIntent.SearchByName(queary))
            },
            active = states.value.searchActive,
            onSearch = {
                viewModel.handleIntents(HomeIntent.SetSearchActive(false))
            },
            onActiveChange = { isActive ->
                viewModel.handleIntents(HomeIntent.SetSearchActive(isActive))
            }
        ) {

        }
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsPaging(movies) { movie, index ->
                MovieExpandedItem(
                    movie = movie,
                    index = index
                )
            }
        }
    }
}