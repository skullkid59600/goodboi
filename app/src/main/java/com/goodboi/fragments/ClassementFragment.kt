package com.goodboi.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.goodboi.R
import com.goodboi.activities.data.ListDog
import com.goodboi.databinding.FragmentClassementBinding
import com.goodboi.utils.fragmentAutoCleared
import kotlinx.coroutines.*
import java.io.IOException
import java.net.URL


class ClassementFragment : Fragment() {
    private var _binding: FragmentClassementBinding by fragmentAutoCleared()
    private var dogList = ListDog()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val customAdapter = CustomAdapter(dogList, this)
//        gridView?.adapter = customAdapter
        _binding = FragmentClassementBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val dogs = dogList

        val img1: Deferred<Bitmap?> = GlobalScope.async {
            val urlImage = URL(dogs.getByIndex(1).url)
            urlImage.toBitmap()
        }
        val img2: Deferred<Bitmap?> = GlobalScope.async {
            val urlImage = URL(dogs.getByIndex(2).url)
            urlImage.toBitmap()
        }
        val img3: Deferred<Bitmap?> = GlobalScope.async {
            val urlImage = URL(dogs.getByIndex(3).url)
            urlImage.toBitmap()
        }
        GlobalScope.launch(Dispatchers.Main) {
            _binding.doge1.setImageBitmap(img1.await())
            _binding.doge2.setImageBitmap(img2.await())
            _binding.doge3.setImageBitmap(img3.await())
        }

        super.onViewCreated(view, savedInstanceState)
    }


    /*
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
    */
    private fun URL.toBitmap(): Bitmap? {
        return try {
            BitmapFactory.decodeStream(openStream())
        } catch (e: IOException) {
            null
        }
    }
}