package com.attila.gerendi.pocketsigns

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Spanned
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.attila.gerendi.pocketsigns.databinding.ActivityEulaBinding
import com.google.android.material.snackbar.Snackbar
import java.io.InputStream


class EulaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEulaBinding
    private lateinit var preferences: SharedPreferences
    private lateinit var eula_content: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = applicationContext
            .getSharedPreferences("com.attila.gerendi.pocketsigns", Context.MODE_PRIVATE)
        binding = ActivityEulaBinding.inflate(layoutInflater)
        eula_content = binding.eulaContent
        setContentView(binding.root)

        val inputStream: InputStream = resources.openRawResource(
            com.attila.gerendi.pocketsigns.R.raw.eula)
        val bytes = ByteArray(inputStream.available())
        inputStream.read(bytes)
        val htmlAsSpanned: Spanned =
            HtmlCompat.fromHtml(String(bytes), HtmlCompat.FROM_HTML_MODE_LEGACY)

        eula_content.text = htmlAsSpanned

        binding.eulaCancel.setOnClickListener { cancelEULA() }
        binding.eulaConfirm.setOnClickListener {confirmEULA() }

    }

    private fun cancelEULA() {
        Snackbar.make(
            findViewById(android.R.id.content),
            getString(com.attila.gerendi.pocketsigns.R.string.must_accept_eula),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun confirmEULA() {
        preferences.edit().putBoolean("eulaAccepted", true).apply()
        val intent = Intent()
        setResult(RESULT_OK, intent)
        finish()
    }

}