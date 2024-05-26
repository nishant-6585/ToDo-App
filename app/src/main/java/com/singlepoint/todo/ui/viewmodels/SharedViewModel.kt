package com.singlepoint.todo.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singlepoint.todo.data.models.Priority
import com.singlepoint.todo.data.models.ToDoTask
import com.singlepoint.todo.data.repository.ToDoRepository
import com.singlepoint.todo.util.Action
import com.singlepoint.todo.util.Constants.MAX_TITLE_LENGTH
import com.singlepoint.todo.util.RequestState
import com.singlepoint.todo.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

    val action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)

    val id: MutableState<Int> = mutableIntStateOf(0)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.LOW)

     val searchAppBarState: MutableState<SearchAppBarState> = mutableStateOf(SearchAppBarState.CLOSED)
     val searchTextState: MutableState<String> = mutableStateOf("")

    private val _allTasks =
        MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<ToDoTask>>> = _allTasks

    fun getAllTasks(){
        _allTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.getAllTasks.collect {
                    _allTasks.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception){
            _allTasks.value = RequestState.Error(e)
        }
    }

    private val _selectedTask: MutableStateFlow<ToDoTask?> = MutableStateFlow(null)
    val selectedTask: StateFlow<ToDoTask?> = _selectedTask
    fun getSelectedTask(
        taskId: Int
    ) {
        viewModelScope.launch {
            repository.getSelectedTask(taskId).collect {task ->
                _selectedTask.value = task
            }
        }
    }

    private fun addTask() {
        viewModelScope.launch {
            repository.addTask(
                ToDoTask(
                    title = title.value,
                    description = description.value,
                    priority = priority.value
                )
            )
        }
    }

    fun handleDatabaseActions(action: Action) {
        when (action) {
            Action.ADD -> {
                addTask()
            }

            Action.UPDATE -> {}
            Action.DELETE -> {}
            Action.DELETE_ALL -> {}
            Action.SELECT -> {}
            Action.UNDO -> {}
            Action.NO_ACTION -> {}
        }
        this.action.value = Action.NO_ACTION
    }

    fun updateTaskFields(selectedTask: ToDoTask?) {
       if(selectedTask != null) {
           id.value = selectedTask.id
           title.value = selectedTask.title
           description.value = selectedTask.description
           priority.value = selectedTask.priority
       } else {
           id.value = 0
           title.value = ""
           description.value = ""
           priority.value = Priority.LOW
       }
    }

    fun updateTitle(
        newTitle: String
    ) {
        if (newTitle.length < MAX_TITLE_LENGTH) {
            title.value = newTitle
        }

    }

    fun validateFields() : Boolean {
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }
}