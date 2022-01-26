package com.attila.gerendi.pocketsigns

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowInsets
import android.widget.ImageView
import com.attila.gerendi.pocketsigns.databinding.FullScreenActivityBinding

class FullScreenViewActivity : Activity() {
    private lateinit var binding: FullScreenActivityBinding
    private lateinit var detailImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FullScreenActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val itemDescriptor = intent.getStringExtra("itemDescriptor").toString()
        val parts = itemDescriptor.split("#").toTypedArray()

        binding.detailText.text = parts[0]

        val identifier = parts[1].replace('/', '_')
        val drawableResourceId: Int = resources.getIdentifier(
            identifier, "drawable",
            packageName
        )

        detailImage = binding.detailImage
        detailImage.setImageResource(drawableResourceId)
        detailImage.adjustViewBounds = true
        Handler(Looper.getMainLooper()).postDelayed(goFullScreen, 3000)

    }

    private val goFullScreen = Runnable {
        // Delayed removal of status and navigation bar
        if (Build.VERSION.SDK_INT >= 30) {
            detailImage.windowInsetsController?.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
        } else {
            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            detailImage.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LOW_PROFILE or
                        View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
    }
}