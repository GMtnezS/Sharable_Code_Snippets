package com.example.m1cta.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.m1cta.data.TodoDatabase
import com.example.m1cta.data.TodoItem
import com.example.m1cta.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TodoRepository
    private val _todos = MutableStateFlow<List<TodoItem>>(emptyList())
    val todos: StateFlow<List<TodoItem>> = _todos.asStateFlow()

    init {
        val todoDao = TodoDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)

        viewModelScope.launch {
            repository.allTodos.collect { todoList ->
                _todos.value = todoList
            }
        }
    }

    fun addTodo(title: String) {
        if (title.isNotBlank()) {
            viewModelScope.launch {
                repository.insert(TodoItem(title = title))
            }
        }
    }

    fun toggleTodoComplete(todo: TodoItem) {
        viewModelScope.launch {
            repository.update(todo.copy(isCompleted = !todo.isCompleted))
        }
    }

    fun deleteTodo(todo: TodoItem) {
        viewModelScope.launch {
            repository.delete(todo)
        }
    }
}