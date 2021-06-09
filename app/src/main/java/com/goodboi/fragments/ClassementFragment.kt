package com.goodboi.fragments

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.goodboi.R
import com.goodboi.activities.data.ListDog
import java.io.IOException
import java.net.URL


class ClassementFragment : Fragment() {
    private var gridView: GridView? = null
    private var dogList = ListDog()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val customAdapter = CustomAdapter(dogList, this)
        gridView?.adapter = customAdapter

        return gridView
    }

    class CustomAdapter(private var dogList: ListDog, var fragment: Fragment) : BaseAdapter() {

        private var layoutInflater =
            fragment.activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getView(position: Int, views: View?, viewGroup: ViewGroup?): View {
            var view = views
            if (view == null) {
                view = layoutInflater.inflate(R.layout.classement_layout, viewGroup, false)
            }
            val point = view?.findViewById<TextView>(R.id.dogText)
            val photoDog = view?.findViewById<ImageView>(R.id.dogPicture)

            for (dog in dogList.dogs) {
                val dogUrl = URL(dog.url)
                point?.text = dog.getPoint().toString()
                photoDog?.setImageBitmap(dogUrl.toBitmap())
            }

            return view!!
        }

        private fun URL.toBitmap(): Bitmap? {
            return try {
                BitmapFactory.decodeStream(openStream())
            } catch (e: IOException) {
                null
            }
        }

        override fun getItem(position: Int): Any {
            return dogList.dogs[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return dogList.dogs.size

        }

    }
}