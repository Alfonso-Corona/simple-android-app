package com.example.firtsapp.imccalculator

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firtsapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class ImcCalculatorActivity : AppCompatActivity() {

  private var isMaleSelected:Boolean = true
  private var isFemaleSelected:Boolean = false
  private var currentWeight:Int = 60
  private var currentAge:Int = 18
  private var currentHeight:Int = 120

  private lateinit var viewMale:CardView
  private lateinit var viewFemale:CardView
  private lateinit var tvHeight:TextView
  private lateinit var rsHeight:RangeSlider
  private lateinit var btnSubtractWeight:FloatingActionButton
  private lateinit var btnAddWeight:FloatingActionButton
  private lateinit var tvWeight:TextView
  private lateinit var btnSubtractAge:FloatingActionButton
  private lateinit var btnAddAge:FloatingActionButton
  private lateinit var tvAge:TextView
  private lateinit var btnCalculate:Button

  companion object{
    const val IMC_KEY = "IMC_RESULT"
  }

  @RequiresApi(Build.VERSION_CODES.N)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_imc_calculator)
    initComponent()
    initListeners()
    initUI()
  }

  private fun initComponent(){
    viewMale = findViewById(R.id.cardMale)
    viewFemale = findViewById(R.id.cardFemale)
    tvHeight = findViewById(R.id.tvHeight)
    rsHeight = findViewById(R.id.rsHeight)
    btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
    btnAddWeight = findViewById(R.id.btnAddWeight)
    tvWeight = findViewById(R.id.tvWeight)
    btnSubtractAge = findViewById(R.id.btnSubtractAge)
    btnAddAge = findViewById(R.id.btnAddAge)
    tvAge = findViewById(R.id.tvAge)
    btnCalculate = findViewById(R.id.btnCalculate)
  }

  @RequiresApi(Build.VERSION_CODES.N)
  private fun initListeners(){
    viewMale.setOnClickListener{
      if(isMaleSelected){
        changeGender()
        setGenderColor()
      }
    }
    viewFemale.setOnClickListener {
        if (isFemaleSelected){
        changeGender()
        setGenderColor()
      }
    }
    rsHeight.addOnChangeListener { _, value, _ ->
      val df = DecimalFormat("#.##")
      currentHeight = df.format(value).toInt()
      tvHeight.text = "$currentHeight cm"
    }
    btnSubtractWeight.setOnClickListener{
      currentWeight--
      setWeight()
    }
    btnAddWeight.setOnClickListener{
      currentWeight++
      setWeight()
    }
    btnAddAge.setOnClickListener{
      currentAge++
      setAge()
    }
    btnSubtractAge.setOnClickListener{
      currentAge--
      setAge()
    }
    btnCalculate.setOnClickListener{
      val result = calculateIMC()
      navigateToResolve(result)
    }
  }

  private fun navigateToResolve(result: Double) {
    val intent = Intent(this, ResultIMCActivity::class.java)
    intent.putExtra(IMC_KEY, result)
    startActivity(intent)
  }

  @RequiresApi(Build.VERSION_CODES.N)
  private fun calculateIMC():Double {
    val df = DecimalFormat("#.##")
    val imc = currentWeight/(currentHeight.toDouble()/100*currentHeight.toDouble()/100)
    return df.format(imc).toDouble()

  }

  private fun setAge() {
    tvAge.text = currentAge.toString()
  }

  private fun setWeight() {
    tvWeight.text = currentWeight.toString()
  }

  private fun changeGender(){
    isMaleSelected = !isMaleSelected
    isFemaleSelected = !isFemaleSelected
  }

  private fun setGenderColor(){
    viewMale.setBackgroundColor(getBackgroundColor(isMaleSelected))
    viewFemale.setBackgroundColor(getBackgroundColor(isFemaleSelected))
  }

  private fun getBackgroundColor(isSelectedComponent:Boolean) : Int{
     val colorReference = if(isSelectedComponent){
       R.color.background_component
     }else{
       R.color.background_component_selected
     }
    return ContextCompat.getColor(this, colorReference)
  }

  private fun initUI() {
    setGenderColor()
    setWeight()
    setAge()
  }

}