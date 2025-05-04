package com.example.firtsapp.todoapp

sealed class TaskCategory {
  object Personal : TaskCategory()
  object Business : TaskCategory()
  object Other : TaskCategory()
}