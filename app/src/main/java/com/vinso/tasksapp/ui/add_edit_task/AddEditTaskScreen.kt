package com.vinso.tasksapp.ui.add_edit_task

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vinso.tasksapp.util.UiEvent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddEditTaskScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditTaskViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
                else -> Unit
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(AddEditTaskEvent.OnSaveTaskClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TextField(
                value = viewModel.name,
                onValueChange = {
                    viewModel.onEvent(AddEditTaskEvent.OnNameChange(it))
                },
                placeholder = {
                    Text(text = "Name")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 5.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = viewModel.description,
                onValueChange = {
                    viewModel.onEvent(AddEditTaskEvent.OnDescriptionChange(it))
                },
                placeholder = {
                    Text(text = "Description")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                singleLine = false,
                maxLines = 5
            )
        }
    }
}