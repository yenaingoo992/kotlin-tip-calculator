package com.kotlin_codelab.ye.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.kotlin_codelab.ye.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        binding.serviceLayout.error = "" // clear the error
        val serviceText = binding.edtService.text.toString()
        val cost = serviceText.toDoubleOrNull()
        if (cost == null) {
            binding.serviceLayout.error = "${binding.serviceLayout.hint} is required!"
            return
        }
        val percentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.tip_amazing -> 0.2
            R.id.tip_good -> 0.18
            else -> 0.15
        }
        val tip = NumberFormat.getCurrencyInstance().format(cost * percentage)
        Toast.makeText(this, tip, Toast.LENGTH_LONG).show()
    }
}