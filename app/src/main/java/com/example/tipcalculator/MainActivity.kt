package com.example.tipcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    // Variable for object binding
    // late init -> will initiate later
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // initialize object binding to access view in activity_main.xml
        binding = ActivityMainBinding.inflate(layoutInflater)

        // set root to binding.root instead of R.layout.activity_main
        setContentView(binding.root)

        // Old way with findViewById()
        // val myButton: Button = findViewById(R.id.my_button)
        // myButton.text = "A button"

        // ViewBinding Way
        // val btnCalculate: Button = binding.buttonCalculate

        // can do one line too
        // binding.buttonCalculate.text = "One liner"

        binding.buttonCalculate.setOnClickListener { calculateTip() }
    }

    /**
     * Method for checking TIP
     */
    private fun calculateTip() {

        // .text -> took text property from it -> Type editable
        val stringEditTextCostOfService = binding.textFieldCostOfService.editText?.text.toString()
        val cost = stringEditTextCostOfService.toString().toDoubleOrNull()
        if (cost == null) {
            binding.tipResult.text = ""
            return
        }

        // take value from radioButton then do when
        val tipPercentage = when (binding.radioGroup.checkedRadioButtonId) {
            R.id.radioButton_twentyPercent -> 0.20
            R.id.radioButton_eightTeenPercent -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost

        // handle round up
        if (binding.switchRoundUp.isChecked) {
            tip = ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

    }
}