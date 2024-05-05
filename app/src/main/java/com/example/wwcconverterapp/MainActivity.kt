package com.example.wwcconverterapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var spinnerFrom: Spinner
    private lateinit var spinnerTo: Spinner
    private lateinit var editTextAmount: EditText
    private lateinit var textViewResult: TextView

    companion object {
        // Rates as two-dimensional array matching the order of currencies in the spinner
        private val rates = arrayOf(
            doubleArrayOf(1.0, 0.85, 0.75, 110.0),   // USD to USD, EUR, GBP, JPY
            doubleArrayOf(1.18, 1.0, 0.88, 129.53),  // EUR to USD, EUR, GBP, JPY
            doubleArrayOf(1.34, 1.13, 1.0, 147.12),  // GBP to USD, EUR, GBP, JPY
            doubleArrayOf(0.0090, 0.0077, 0.0068, 1.0) // JPY to USD, EUR, GBP, JPY
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerFrom = findViewById(R.id.spinnerFrom)
        spinnerTo = findViewById(R.id.spinnerTo)
        editTextAmount = findViewById(R.id.editTextAmount)
        textViewResult = findViewById(R.id.textViewResult)
        val buttonConvert: Button = findViewById(R.id.buttonConvert)

        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.currency_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFrom.adapter = adapter
        spinnerTo.adapter = adapter

        buttonConvert.setOnClickListener {
            convertCurrency()
        }
    }

    private fun convertCurrency() {
        val fromIndex = spinnerFrom.selectedItemPosition
        val toIndex = spinnerTo.selectedItemPosition
        val amountStr = editTextAmount.text.toString()

        if (amountStr.isNotEmpty()) {
            val amount = amountStr.toDouble()
            val rate = rates[fromIndex][toIndex]
            val result = amount * rate
            textViewResult.text = String.format("%.2f", result)
        } else {
            textViewResult.text = "Please enter an amount"
        }
    }
}
