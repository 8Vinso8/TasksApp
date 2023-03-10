package com.vinso.tasksapp.ui.task_list

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vinso.tasksapp.data.task.Task

@Composable
fun TaskItem(
    task: Task,
    onEvent: (TaskListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = task.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = {
                    onEvent(TaskListEvent.OnDeleteTaskClick(task))
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }
            }
            task.description?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it)
            }
        }
        Column(
            modifier = Modifier.weight(0.2f),
            verticalArrangement = Arrangement.Center
        ) {
            Checkbox(
                checked = task.isDone,
                onCheckedChange = { isChecked ->
                    onEvent(TaskListEvent.OnDoneChange(task, isChecked))
                }
            )
            Checkbox(checked = task.isFavourite, onCheckedChange = { isChecked ->
                onEvent(TaskListEvent.OnFavouriteChange(task, isChecked))})
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TaskItemPreview() {
    val voidFun: (TaskListEvent) -> Unit = {}
    TaskItem(
        task = Task(
            name = "Preview title",
            description = "Preview description",
            isDone = false,
            isFavourite = false,
        ),
        onEvent = voidFun
    )
}