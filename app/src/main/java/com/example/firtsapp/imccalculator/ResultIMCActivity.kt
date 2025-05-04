package com.example.firtsapp.imccalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.firtsapp.R
import com.example.firtsapp.imccalculator.ImcCalculatorActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

  private lateinit var tvResult: TextView
  private lateinit var tvIMCResult: TextView
  private lateinit var tvDescription: TextView
  private lateinit var btnReCalculate: Button

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_result_imcactivity)
    val result = intent.extras?.getDouble(IMC_KEY)?: -1.0
    initComponents()
    initUI(result)
    initListeners()
  }

  private fun initListeners() {
    btnReCalculate.setOnClickListener{
      onBackPressedDispatcher.onBackPressed()
    }
  }

  private fun initUI(result: Double) {
    tvIMCResult.text = result.toString()
    when(result){
      in 0.00..18.50 -> {//Bajo peso
        tvResult.text = getString(R.string.low_weight)
        tvResult.setTextColor(ContextCompat.getColor(this, R.color.low))
        tvDescription.text = getString(R.string.description_low_weight)
      }
      in 18.51..24.99 -> {//Peso normal
        tvResult.text = getString(R.string.normal_weight)
        tvResult.setTextColor(ContextCompat.getColor(this, R.color.normal))
        tvDescription.text = getString(R.string.description_normal_weight)
      }
      in 25.00..29.99 -> {//Sobre peso
        tvResult.text = getString(R.string.high_weight)
        tvResult.setTextColor(ContextCompat.getColor(this, R.color.high))
        tvDescription.text = getString(R.string.description_high_weight)
      }
      in 30.00..99.99 -> {//Obesidad
        tvResult.text = getString(R.string.obesity)
        tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesity))
        tvDescription.text = getString(R.string.description_obesity)
      }
      else -> { //error
        tvResult.text = getString(R.string.error)
        tvIMCResult.text = getString(R.string.error)
        tvDescription.text = getString(R.string.error)
      }
    }
  }

  private fun initComponents(){
    tvIMCResult = findViewById(R.id.tvIMCResult)
    tvDescription = findViewById(R.id.tvDescription)
    tvResult = findViewById(R.id.tvResultIMC)
    btnReCalculate = findViewById(R.id.btnReCalculate)
  }


}