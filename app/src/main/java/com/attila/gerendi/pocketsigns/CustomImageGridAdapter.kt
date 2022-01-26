package com.attila.gerendi.pocketsigns

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomImageGridAdapter(var items: Array<String>, var context: Context): BaseAdapter(){

    var layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(idx: Int): Any {
        return items[idx]
    }

    override fun getItemId(idx: Int): Long {
        return idx.toLong()
    }

    override fun getView(idx: Int, reView: View?, viewGroup: ViewGroup?): View {
        var view = reView
        if(view == null)
            view = layoutInflater.inflate(R.layout.grid_element, viewGroup, false)

        val title = view?.findViewById<TextView>(R.id.grid_element_title)
        val img = view?.findViewById<ImageView>(R.id.grid_element_image)

        val parts = items[idx].split("#").toTypedArray()

        title?.text = parts[0]

        val identifier = parts[1].replace('/', '_')

        val drawableResourceId: Int = context.resources.getIdentifier(
            identifier, "drawable",
            context.packageName
        )
        img?.setImageResource(drawableResourceId)
        img?.adjustViewBounds = true
        return view!!
    }

}