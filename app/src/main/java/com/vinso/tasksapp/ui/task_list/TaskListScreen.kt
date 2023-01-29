package com.vinso.tasksapp.ui.task_list

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.vinso.tasksapp.data.task.Task
import com.vinso.tasksapp.util.UiEvent
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TaskListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: TaskListViewModel = hiltViewModel()
) {
    val allTasks = viewModel.allTasks.collectAsState(initial = emptyList())
    val favouriteTasks = viewModel.favouriteTasks.collectAsState(initial = emptyList())
    val doneTasks = viewModel.doneTasks.collectAsState(initial = emptyList())
    val undoneTasks = viewModel.undoneTasks.collectAsState(initial = emptyList())

    val composableScope = rememberCoroutineScope()


    val pagerState = rememberPagerState()
    val title: String = when (pagerState.currentPage) {
        0 -> "Favourite"
        1 -> "Not done"
        2 -> "Done"
        else -> "All"
    }

    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(TaskListEvent.OnUndoDeleteClick)
                    }
                }
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                        )
                    }
                ) {
                    listOf<String>("Favourite", "Not done", "Done", "All").forEachIndexed { index, title ->
                        Tab(
                            content = { Text(title)},
                            selected = pagerState.currentPage == index,
                            onClick = {composableScope.launch { pagerState.animateScrollToPage(index) } }
                        )
                    }
                } },
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(TaskListEvent.OnAddTaskClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) {

        HorizontalPager(
            count = 4,
            state = pagerState,
        ) {
            when (it) {
                0 -> TaskList(
                    tasks = favouriteTasks.value,
                    onEvent = viewModel::onEvent,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
                1 -> TaskList(
                    tasks = undoneTasks.value,
                    onEvent = viewModel::onEvent,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
                2 -> TaskList(
                    tasks = doneTasks.value,
                    onEvent = viewModel::onEvent,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
                3 -> TaskList(
                    tasks = allTasks.value,
                    onEvent = viewModel::onEvent,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun TaskList(
    tasks: List<Task>,
    onEvent: (TaskListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(tasks) { task ->
            TaskItem(
                task = task,
                onEvent = onEvent,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onEvent(TaskListEvent.OnTaskClick(task))
                    }
                    .padding(16.dp)
            )
        }
    }
}
