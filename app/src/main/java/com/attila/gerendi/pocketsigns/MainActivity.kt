package com.attila.gerendi.pocketsigns

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import com.attila.gerendi.pocketsigns.databinding.MainActivityBinding


class MainActivity : Activity() {

    private lateinit var binding: MainActivityBinding
    private lateinit var fullscreenContent: TextView
    private lateinit var categoryChose: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        fullscreenContent = binding.categoryChoseTitle
        categoryChose = binding.categoryGridView

        val category = resources.getStringArray(R.array.categories)
        categoryChose.adapter = CustomImageGridAdapter(category, this)
        categoryChose.setOnItemClickListener {
            parent, view, position, id ->
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("categoryIndex", position)
            startActivity(intent)
        }

        checkEula()

    }

    private fun checkEula() {
        val preferences = applicationContext
            .getSharedPreferences("com.attila.gerendi.pocketsigns", Context.MODE_PRIVATE)
        val eulaAccepted = preferences.getBoolean("eulaAccepted", false)
        if (!eulaAccepted) {
            val intent = Intent(this, EulaActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

}