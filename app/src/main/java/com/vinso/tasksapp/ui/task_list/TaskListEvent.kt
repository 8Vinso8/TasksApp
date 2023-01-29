package com.vinso.tasksapp.ui.task_list

import com.vinso.tasksapp.data.task.Task

sealed class TaskListEvent {
    data class OnDeleteTaskClick(val task: Task) : TaskListEvent()
    data class OnDoneChange(val task: Task, val isDone: Boolean) : TaskListEvent()
    data class OnFavouriteChange(val task: Task, val isFavourite: Boolean) : TaskListEvent()
    object OnUndoDeleteClick : TaskListEvent()
    data class OnTaskClick(val task: Task) : TaskListEvent()
    object OnAddTaskClick : TaskListEvent()
}

