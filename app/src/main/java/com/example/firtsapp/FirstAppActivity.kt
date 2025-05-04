package com.example.firtsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FirstAppActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_first_app)
    val btnStart = findViewById<AppCompatButton>(R.id.btnStart)
    val etName = findViewById<AppCompatEditText>(R.id.etName)
    btnStart.setOnClickListener{
      val text = etName.text.toString()
      if(text.isNotEmpty()){
        //Log.i("aDiff", "Boton pulsado por ${etName.text.toString()}")
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("EXTRA_NAME", text)
        startActivity(intent)
      }
    }
  }
}