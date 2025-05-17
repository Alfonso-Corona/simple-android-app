package com.example.firtsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firtsapp.imccalculator.ImcCalculatorActivity
import com.example.firtsapp.pokeapi.PokeApiActivity
import com.example.firtsapp.todoapp.TodoActivity

class MenuActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_menu)
    val btnSaludApp = findViewById<Button>(R.id.btnSaludApp)
    val btnIMCApp = findViewById<Button>(R.id.btnIMCApp)
    val btnTODO = findViewById<Button>(R.id.btnTODO)
    val btnPokemon = findViewById<Button>(R.id.btnPokemon)
    btnSaludApp.setOnClickListener{ navigateToSaludApp() }
    btnIMCApp.setOnClickListener{ navigateToIMCApp() }
    btnTODO.setOnClickListener{ navigateToTODOApp() }
    btnPokemon.setOnClickListener { navigateToPokeApi() }
  }

  private fun navigateToSaludApp(){
    val intent = Intent(this, FirstAppActivity::class.java)
    startActivity(intent)
  }

  private fun navigateToIMCApp(){
    val intent = Intent(this, ImcCalculatorActivity::class.java)
    startActivity(intent)
  }

  private fun navigateToTODOApp(){
    val intent = Intent(this, TodoActivity::class.java)
    startActivity(intent)
  }

  private fun navigateToPokeApi(){
    val intent = Intent(this, PokeApiActivity::class.java)
    startActivity(intent)
  }
}