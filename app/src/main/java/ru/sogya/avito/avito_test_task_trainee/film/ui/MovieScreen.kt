package ru.sogya.avito.avito_test_task_trainee.film.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.sogya.avito.avito_test_task_trainee.R
import ru.sogya.avito.avito_test_task_trainee.core.uikit.component.HSpacer
import ru.sogya.avito.avito_test_task_trainee.core.uikit.component.OnEffect
import ru.sogya.avito.avito_test_task_trainee.core.uikit.component.VSpacer
import ru.sogya.avito.avito_test_task_trainee.core.uikit.component.itemsPaging
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.FullMovie
import ru.sogya.avito.avito_test_task_trainee.film.domain.entity.Poster
import ru.sogya.avito.avito_test_task_trainee.film.ui.component.PersonItem
import ru.sogya.avito.avito_test_task_trainee.film.ui.component.ReviewItem
import ru.sogya.avito.avito_test_task_trainee.ui.theme.TestAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    movieId: Int?,
    viewModel: MovieVM = hiltViewModel(),
    navigateOnBack: () -> Unit
) {
    val state = viewModel.uiState.collectAsState()
    val reviewItems = state.value.reviews.collectAsLazyPagingItems()
    val posterItems = state.value.posters.collectAsLazyPagingItems()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    LaunchedEffect(state) {
        movieId?.let { MovieIntent.InitMovie(it) }?.let { viewModel.handleIntents(it) }
    }
    OnEffect(viewModel.effect) { effect ->
        when (effect) {
            MovieEffect.OnBackInvoked -> navigateOnBack()
        }
    }
    if (state.value.loading) {
        println("load")
    } else {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = { Text(text = state.value.movie.name) },
                    navigationIcon = {
                        IconButton(onClick = { navigateOnBack() }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                        }
                    },
                    scrollBehavior = scrollBehavior
                )
            },
            content = { padding ->
                with(state.value.movie) {
                    LazyColumn(modifier = Modifier.fillMaxSize().padding(padding)) {
                        item {
                            MovieHeader(this@with)
                            Text(
                                modifier = Modifier.padding(10.dp),
                                textAlign = TextAlign.Justify,
                                style = TestAppTheme.typography.p2Normal,
                                text = description
                            )
                            MovieMainContent(this@with, posterItems)
                        }
                        itemsPaging(reviewItems) { review, _ ->
                            ReviewItem(review)
                        }
                    }
                }
            })
    }
}

@Composable
private fun MovieHeader(
    movie: FullMovie
) = Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    AsyncImage(
        modifier = Modifier.fillMaxWidth().height(200.dp),
        contentScale = ContentScale.Crop,
        model = ImageRequest.Builder(LocalContext.current)
            .data(movie.backdrop.url)
            .build(),
//            placeholder = painterResource(MR.images.employee_placheholer.drawableResId),
        filterQuality = FilterQuality.High,
        contentDescription = null
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier.padding(top = 5.dp, bottom = 2.dp),
            style = TestAppTheme.typography.h1,
            text = movie.name
        )
        Row {
            Text(
                text = movie.rating.imdb.toString(),
                style = TestAppTheme.typography.p4Light
            )
            HSpacer(size = 5.dp)
            Text(
                text = movie.type,
                style = TestAppTheme.typography.p4Light
            )
        }
        VSpacer(size = 1.dp)
        Row {
            Text(
                text = movie.countries[0].name,
                style = TestAppTheme.typography.p4Light
            )
            HSpacer(size = 5.dp)
            Text(
                text = movie.genres[0].name,
                style = TestAppTheme.typography.p4Light
            )
        }
    }
}

@Composable
private fun MovieMainContent(
    movie: FullMovie,
    posters: LazyPagingItems<Poster>
) = Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 5.dp)) {
    Text(
        modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
        style = TestAppTheme.typography.h2,
        text = stringResource(R.string.movie_screen_actors_title)
    )
    LazyHorizontalGrid(
        modifier = Modifier.height(200.dp),
        rows = GridCells.Fixed(3)
    ) {
        items(movie.persons) { person ->
            PersonItem(person)
        }
    }
    Text(
        modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
        style = TestAppTheme.typography.h2,
        text = stringResource(R.string.movie_screen_posters_title)
    )
    LazyRow(modifier = Modifier.fillMaxWidth().heightIn(max = 500.dp)) {
        itemsPaging(posters) { poster, _ ->
            AsyncImage(
                modifier = Modifier.width(100.dp).height(150.dp).padding(5.dp),
                contentScale = ContentScale.Crop,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(poster.url)
                    .build(),
//            placeholder = painterResource(MR.images.employee_placheholer.drawableResId),
                filterQuality = FilterQuality.High,
                contentDescription = null
            )
        }
    }
    Text(
        modifier = Modifier.padding(top = 10.dp),
        style = TestAppTheme.typography.h2,
        text = stringResource(R.string.movie_screen_reviews_title)
    )
}