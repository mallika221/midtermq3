package com.example.midtermq3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val openCalculatorButton = findViewById<Button>(R.id.openCalculatorButton)
        val openCityFactsButton = findViewById<Button>(R.id.openCityFactsButton)

        openCalculatorButton.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java)
            intent.putExtra("initialValue", 0)
            startActivity(intent)
        }

        openCityFactsButton.setOnClickListener {
            val intent = Intent(this, CityFactsActivity::class.java)
            intent.putExtra("initialCity", "New York")
            startActivity(intent)
        }
    }
}
