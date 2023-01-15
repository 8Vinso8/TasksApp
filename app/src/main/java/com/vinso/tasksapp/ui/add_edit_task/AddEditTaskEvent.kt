package com.vinso.tasksapp.ui.add_edit_task

sealed class AddEditTaskEvent {
    data class OnNameChange(val name: String) : AddEditTaskEvent()
    data class OnDescriptionChange(val description: String) : AddEditTaskEvent()
    object OnSaveTaskClick : AddEditTaskEvent()
}
