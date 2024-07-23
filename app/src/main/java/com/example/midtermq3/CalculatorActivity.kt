package com.example.midtermq3

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val number1EditText = findViewById<EditText>(R.id.number1)
        val number2EditText = findViewById<EditText>(R.id.number2)
        val operationSpinner = findViewById<Spinner>(R.id.operation)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val resultTextView = findViewById<TextView>(R.id.result)

        val initialValue = intent.getIntExtra("initialValue", 0)
        number1EditText.setText(initialValue.toString())

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.operations,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            operationSpinner.adapter = adapter
        }

        calculateButton.setOnClickListener {
            val number1 = number1EditText.text.toString().toDoubleOrNull()
            val number2 = number2EditText.text.toString().toDoubleOrNull()
            val operation = operationSpinner.selectedItem.toString()

            if (number1 == null || number2 == null) {
                Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val result = when (operation) {
                "Addition" -> number1 + number2
                "Subtraction" -> number1 - number2
                "Multiplication" -> number1 * number2
                "Division" -> {
                    if (number2 == 0.0) {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    } else {
                        number1 / number2
                    }
                }
                else -> 0.0
            }

            resultTextView.text = "Result: $result"
        }
    }
}
