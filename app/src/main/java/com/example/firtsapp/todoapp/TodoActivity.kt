package com.example.firtsapp.todoapp

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firtsapp.R
import com.example.firtsapp.todoapp.TaskCategory.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoActivity : AppCompatActivity() {

  private val categories = listOf(
    Business,
    Personal,
    Other
  )

  private val tasks = mutableListOf(
    Task("Prueba Business", Business),
    Task("Prueba Personal", Personal),
    Task("Prueba Other", Other),
  )

  private lateinit var rvCategories: RecyclerView
  private lateinit var categoriesAdapter: CategoriesAdapter

  private lateinit var rvTasks: RecyclerView
  private lateinit var tasksAdapter: TasksAdapter

  private lateinit var fabAddTask: FloatingActionButton

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_todo)
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }
    initComponent()
    initUI()
    initListeners()
  }

  private fun initListeners() {
    fabAddTask.setOnClickListener{
      showDialog()
    }
  }

  private fun showDialog() {
    val dialog = Dialog(this)
    dialog.setContentView(R.layout.dialog_task)

    val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
    val etTaks: EditText = dialog.findViewById(R.id.etTask)
    val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)

    btnAddTask.setOnClickListener{
      val currentTask = etTaks.text.toString()
      if(currentTask.isNotEmpty()){
        val selectedId = rgCategories.checkedRadioButtonId
        val selectedRadioButton = rgCategories.findViewById<RadioButton>(selectedId)
        val currentCategory: TaskCategory = when(selectedRadioButton.text){
          getString(R.string.todo_business) -> Business
          getString(R.string.todo_personal) -> Personal
          else -> Other
        }

        tasks.add(Task(etTaks.text.toString(), currentCategory))
        updateTask()
        dialog.hide()
      }
    }

    dialog.show()
  }

  private fun initComponent() {
    rvCategories = findViewById(R.id.rvCategories)
    rvTasks = findViewById(R.id.rvTasks)
    fabAddTask = findViewById(R.id.fabAddTask)
  }

  private fun initUI(){
    categoriesAdapter = CategoriesAdapter(categories) {position -> updateCategories(position)}
    rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    rvCategories.adapter = categoriesAdapter

    tasksAdapter = TasksAdapter(tasks) {position -> onItemSelected(position)}
    rvTasks.layoutManager = LinearLayoutManager(this)
    rvTasks.adapter = tasksAdapter
  }

  private fun onItemSelected(position: Int){
    tasks[position].isSelected = !tasks[position].isSelected
    updateTask()
  }

  private fun updateTask(){
    val selectedCategories: List<TaskCategory> = categories.filter { it.isSelected }
    val newTasks = tasks.filter { selectedCategories.contains(it.category) }
    tasksAdapter.tasks = newTasks
    tasksAdapter.notifyDataSetChanged()
  }

  private fun updateCategories(position: Int){
    categories[position].isSelected = !categories[position].isSelected
    categoriesAdapter.notifyItemChanged(position)
    updateTask()
  }

}