package com.attila.gerendi.pocketsigns

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.attila.gerendi.pocketsigns.databinding.CategoryActivityBinding


class CategoryActivity : Activity() {
    private lateinit var itemDescriptors: Array<String>
    private lateinit var binding: CategoryActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CategoryActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryIndex = intent.getIntExtra("categoryIndex", 0)

        itemDescriptors = resources.getStringArray(R.array.road_signs)
        when (categoryIndex) {
            0 -> itemDescriptors = resources.getStringArray(R.array.road_signs)
            1 -> itemDescriptors = resources.getStringArray(R.array.hazard_signs)
            2 -> itemDescriptors = resources.getStringArray(R.array.prohibition_signs)
            3 -> itemDescriptors = resources.getStringArray(R.array.fun_signs)
        }

        val categories = resources.getStringArray(R.array.categories)
        val grid = binding.itemChooserGridView
        val title = binding.ItemChooserTitle
        title.text = categories[categoryIndex].split("#")[0]
        grid.adapter = CustomImageGridAdapter(itemDescriptors, this)
        grid.setOnItemClickListener {
                parent, view, position, id ->
            val intent = Intent(this, FullScreenViewActivity::class.java)
            intent.putExtra("itemDescriptor", itemDescriptors[position])
            startActivity(intent)
        }
    }
}