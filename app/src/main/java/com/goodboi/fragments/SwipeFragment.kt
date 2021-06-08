package com.goodboi.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.goodboi.activities.data.Dog
import com.goodboi.activities.data.ImageViewModelFactory
import com.goodboi.activities.data.ListDog
import com.goodboi.activities.data.Repository.ImageRepository
import com.goodboi.databinding.FragmentSwipeBinding
import com.goodboi.fragments.viewModel.ImageViewModel
import com.goodboi.fragments.viewModel.myResponse
import com.goodboi.utils.fragmentAutoCleared
import kotlinx.coroutines.*
import java.io.IOException
import java.net.URL

class SwipeFragment : Fragment() {
    private var _binding: FragmentSwipeBinding by fragmentAutoCleared()

    //Implement API Images
    private lateinit var imageViewModel: ImageViewModel

    //List des dog
    var dogs = ListDog()
    private lateinit var  choix1 : Dog
    private lateinit var  choix2 : Dog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSwipeBinding.inflate(inflater, container, false)
        choose2Dogs()
        updateImages()

        _binding.heartImage1.setOnClickListener {
            //On vote pour la 1e image
            choix1.versus(0, choix2)
            println(choix1.toString() + choix2.toString())
            choose2Dogs()
            updateImages()
        }
        _binding.heartImage2.setOnClickListener {
            //On vote pour la 2e image
            choix1.versus(1, choix2)
            println(choix1.toString() + choix2.toString())
            choose2Dogs()
            updateImages()
        }

        return _binding.root
    }

    //TODO mettre cette fonction dans ListDog et l'appeler 10 fois dans Init pour remplacer l'URL
    private fun makeImageRequest(imageView: ImageView) {
        val repository = ImageRepository()
        val viewModelFactory = ImageViewModelFactory(repository)
        var res = URL("https://google.fr")
        imageViewModel = ViewModelProvider(this, viewModelFactory).get(ImageViewModel::class.java)
        imageViewModel.getImage()
        myResponse.observe(viewLifecycleOwner, Observer { response ->
            if(response.isSuccessful){
                val urlImage = URL(response.body()?.message!!)
                Log.d("image", urlImage.toString())
                val result: Deferred<Bitmap?> = GlobalScope.async {
                    urlImage.toBitmap()
                }
                GlobalScope.launch(Dispatchers.Main) {
                    imageView.setImageBitmap(result.await())
                }
            }else{
                Log.d("Response", response.errorBody().toString())
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.name = "SWIPE FRAGMENT"
    }

    fun URL.toBitmap(): Bitmap?{
        return try {
            BitmapFactory.decodeStream(openStream())
        }catch (e: IOException){
            null
        }
    }

    private fun updateImages() {
        val img1: Deferred<Bitmap?> = GlobalScope.async {
            val urlImage = URL(choix1.url)
            urlImage.toBitmap()
        }
        val img2: Deferred<Bitmap?> = GlobalScope.async {
            val urlImage = URL(choix2.url)
            urlImage.toBitmap()
        }
        GlobalScope.launch(Dispatchers.Main) {
            _binding.dogImage1.setImageBitmap(img1.await())
            _binding.dogImage2.setImageBitmap(img2.await())
        }
    }

    //choisie 2 dog aléatoirement de la listDog
    private fun choose2Dogs() {
        var rand1 = (0 until dogs.nombre).random()
        var rand2 = -1
        do {
            rand2 = (0 until dogs.nombre).random()
        }while (rand1 == rand2)
        choix1 = dogs.getByIndex(rand1)
        choix2 = dogs.getByIndex(rand2)

    }

}