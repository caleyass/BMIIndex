package com.example.bmiindex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<TextView>(R.id.etWeight)
        val heightText = findViewById<TextView>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalculate)

        calcButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if(validateInput(weight, height)) {
                val bmi = (weight.toFloat() * 10000) / (height.toFloat() * height.toFloat())
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2Digits)
            }
        }
    }
    /**
     * Checks whether entered weight and height are valid
     * @param weight entered weight
     * @param height entered height
     * */
    private fun validateInput(weight:String?, height:String?):Boolean{
        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_LONG).show()
                false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Height is empty", Toast.LENGTH_LONG).show()
                false
            }
            else -> true
        }
    }
    /**
     * Displays result with an appropriate description and color
     * @param bmi calculated BMI index
     * */
    private fun displayResult(bmi:Float){
        val resultIndex = findViewById<TextView>(R.id.tvBMI)
        val resultText = findViewById<TextView>(R.id.tvResult)
        val description = findViewById<TextView>(R.id.tvDescription)

        resultIndex.text = bmi.toString()
        description.text = "Normal range is 18.5 - 24.9"

        var result = ""
        var color = 0
        when{
            bmi<18.50 -> {
                result = "Underweight"
                color = R.color.underweight
            }
            bmi< 24.99 ->{
                result = "Healthy"
                color = R.color.healthy
            }
            bmi < 29.99 ->{
                result = "Overweight"
                color = R.color.overweight
            }
            bmi >=29.99 -> {
                result = "Obese"
                color = R.color.obese
            }
        }
        resultText.setTextColor(ContextCompat.getColor(this, color))
        resultText.text = result
    }
}