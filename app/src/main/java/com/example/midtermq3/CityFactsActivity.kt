package com.example.midtermq3

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CityFactsActivity : AppCompatActivity() {

    private lateinit var citySpinner: Spinner
    private lateinit var selectButton: Button
    private lateinit var selectedCityTextView: TextView
    private var selectedCity: String? = null
    private var cityFacts: Array<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_facts)

        citySpinner = findViewById(R.id.citySpinner)
        selectButton = findViewById(R.id.selectButton)
        selectedCityTextView = findViewById(R.id.selectedCityTextView)

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.cities_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            citySpinner.adapter = adapter
        }

        // Load city facts from resources
        cityFacts = resources.getStringArray(R.array.city_facts_array)

        // Get the initial city from the intent
        val initialCity = intent.getStringExtra("initialCity")
        val initialPosition = resources.getStringArray(R.array.cities_array).indexOf(initialCity)
        if (initialPosition >= 0) {
            citySpinner.setSelection(initialPosition)
        }

        citySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedCity = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedCity = null
            }
        }

        selectButton.setOnClickListener {
            val position = citySpinner.selectedItemPosition
            val fact = cityFacts?.get(position)
            selectedCityTextView.text = "Fact about $selectedCity: $fact"
        }
    }
}
