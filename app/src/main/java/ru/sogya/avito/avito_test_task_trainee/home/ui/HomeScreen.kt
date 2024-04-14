package ru.sogya.avito.avito_test_task_trainee.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.paging.compose.collectAsLazyPagingItems
import ru.sogya.avito.avito_test_task_trainee.R
import ru.sogya.avito.avito_test_task_trainee.core.uikit.component.MovieExpandedItem
import ru.sogya.avito.avito_test_task_trainee.core.uikit.component.MovieSmallItem
import ru.sogya.avito.avito_test_task_trainee.core.uikit.component.OnEffect
import ru.sogya.avito.avito_test_task_trainee.core.uikit.component.VSpacer
import ru.sogya.avito.avito_test_task_trainee.core.uikit.component.itemsPaging
import ru.sogya.avito.avito_test_task_trainee.ui.theme.TestAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToMovieScreen: (movieId: Int) -> Unit,
    viewModel: HomeVM = hiltViewModel<HomeVM>()
) {
    val states = viewModel.uiState.collectAsState()
    val movies = states.value.movies.collectAsLazyPagingItems()
    val isVisible = rememberSaveable { mutableStateOf(true) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (available.y < -1) {
                    isVisible.value = false
                }

                if (available.y > 1) {
                    isVisible.value = true
                }

                return Offset.Zero
            }
        }
    }

    OnLifecycleEvent { _, event ->
        if (event == Lifecycle.Event.ON_START) {
            viewModel.handleIntents(
                HomeIntent.FilterByParams(
                    ageRating = states.value.ageRatingFilter,
                    countries = states.value.countiresFilter,
                    year = states.value.yearFilter
                )
            )
        }
    }
    OnEffect(effects = viewModel.effect) { effect ->
        when (effect) {
            is HomeEffect.NavigateToMovieScreen -> onNavigateToMovieScreen(effect.movieId)
        }
    }
    if (states.value.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = TestAppTheme.colors.accent)
        }
    } else
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                SearchBar(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                    query = states.value.searchQuery,
                    placeholder = {
                        Text(text = stringResource(R.string.search_hint))
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_search_24),
                            contentDescription = null
                        )
                    },
                    onQueryChange = { query ->
                        viewModel.handleIntents(HomeIntent.SearchByName(query))
                    },
                    active = states.value.searchActive,
                    onSearch = {
                        viewModel.handleIntents(HomeIntent.SetSearchActive(false))
                    },
                    onActiveChange = { isActive ->
                        viewModel.handleIntents(HomeIntent.SetSearchActive(isActive))
                    }
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        itemsIndexed(states.value.searches) { _, searchItem ->
                            MovieSmallItem(
                                search = searchItem,
                            ) { search ->
                                viewModel.handleIntents(HomeIntent.OpenMovieFromSearch(search))
                            }
                        }
                    }
                }
            },
            floatingActionButton = {
                AnimatedVisibility(
                    visible = isVisible.value,
                    enter = slideInVertically(initialOffsetY = { it * 2 }),
                    exit = slideOutVertically(targetOffsetY = { it * 2 }),
                ) {
                    ExtendedFloatingActionButton(
                        onClick = { viewModel.handleIntents(HomeIntent.OpenFilterBottomSheet) },
                        icon = { Icon(Icons.Filled.MoreVert, null) },
                        text = {
                            Text(
                                text = stringResource(R.string.filter_button),
                                style = TestAppTheme.typography.p1
                            )
                        },
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.Center
        ) { paddings ->
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(paddings)
                    .nestedScroll(nestedScrollConnection),
            ) {
                itemsPaging(movies) { movie, index ->
                    MovieExpandedItem(
                        movie = movie,
                        index = index
                    ) { movieId ->
                        viewModel.handleIntents(HomeIntent.OpenMovie(movieId))
                    }
                }
            }
            if (states.value.isFilterBottomSheetOpen) {
                FilterBottomSheet(viewModel = viewModel, state = states) {
                    viewModel.handleIntents(HomeIntent.CloseFilterBottomSheet)
                }
            }
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterBottomSheet(
    viewModel: HomeVM,
    state: State<HomeViewState>,
    closeBottomSheet: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { closeBottomSheet() },
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 5.dp)) {
            Text(
                text = stringResource(R.string.filter_year_title),
                style = TestAppTheme.typography.p1
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.value.yearFilter,
                placeholder = {
                    Text(
                        text = stringResource(R.string.filter_year_hint),
                        style = TestAppTheme.typography.p2
                    )
                },
                onValueChange = { year ->
                    viewModel.handleIntents(HomeIntent.FilterByYear(year))
                }
            )
            VSpacer(size = 10.dp)
            Text(
                text = stringResource(R.string.filter_age_rating_title),
                style = TestAppTheme.typography.p1
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.value.ageRatingFilter,
                placeholder = {
                    Text(
                        text = stringResource(R.string.filter_age_rating_hint),
                        style = TestAppTheme.typography.p2
                    )
                },
                onValueChange = { ageRating ->
                    viewModel.handleIntents(HomeIntent.FilterByAgeRating(ageRating))
                }
            )
            VSpacer(size = 10.dp)
            Text(
                text = stringResource(R.string.filter_country_title),
                style = TestAppTheme.typography.p1
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.value.countiresFilter,
                placeholder = {
                    Text(
                        text = stringResource(R.string.filter_country_hint),
                        style = TestAppTheme.typography.p2
                    )
                },
                onValueChange = { countries ->
                    viewModel.handleIntents(HomeIntent.FilterByCountries(countries))
                }
            )
            VSpacer(size = 50.dp)
            Button(
                modifier = Modifier.fillMaxWidth().padding(bottom = 25.dp),
                onClick = {
                    viewModel.handleIntents(
                        HomeIntent.FilterByParams(
                            ageRating = state.value.ageRatingFilter,
                            countries = state.value.countiresFilter,
                            year = state.value.yearFilter
                        )
                    )
                },
                enabled = state.value.setFilterButtonEnabled,
                colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = TestAppTheme.colors.accentDisabled,
                    containerColor = TestAppTheme.colors.accent
                )
            ) {
                Text(
                    stringResource(R.string.filter_button_accept),
                    style = TestAppTheme.typography.p1
                )
            }
        }
    }
}

@Composable
fun OnLifecycleEvent(onEvent: (owner: LifecycleOwner, event: Lifecycle.Event) -> Unit) {
    val eventHandler = rememberUpdatedState(onEvent)
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)
    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle
        val observer = LifecycleEventObserver { owner, event ->
            eventHandler.value(owner, event)
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}
