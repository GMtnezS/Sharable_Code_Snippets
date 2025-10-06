package com.example.m1cta.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_items ORDER BY id DESC")
    fun getAllTodos(): Flow<List<TodoItem>>

    @Insert
    suspend fun insert(todo: TodoItem)

    @Update
    suspend fun update(todo: TodoItem)

    @Delete
    suspend fun delete(todo: TodoItem)
}