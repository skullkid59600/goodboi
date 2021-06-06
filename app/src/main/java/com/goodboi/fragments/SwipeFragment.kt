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
import com.goodboi.R
import com.goodboi.activities.data.ImageViewModelFactory
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSwipeBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.name = "SWIPE FRAGMENT"

        val repository = ImageRepository()
        val viewModelFactory = ImageViewModelFactory(repository)
        val imageView = view.findViewById<ImageView>(R.id.dogImage1)
        val imageView2 = view.findViewById<ImageView>(R.id.dogImage2)
        imageViewModel = ViewModelProvider(this, viewModelFactory).get(ImageViewModel::class.java)
        imageViewModel.getImage()
        myResponse.observe(viewLifecycleOwner, Observer { response ->
            if(response.isSuccessful) {
                val urlImage = URL(response.body()?.message!!)
                Log.d("image", urlImage.toString())
                val result: Deferred<Bitmap?> = GlobalScope.async {
                    urlImage.toBitmap()
                }
                GlobalScope.launch(Dispatchers.Main) {
                    imageView?.setImageBitmap(result.await())
                }
            }
        })
        imageViewModel.getImage()
        myResponse.observe(viewLifecycleOwner, Observer { response ->
            if(response.isSuccessful) {
                val urlImage2 = URL(response.body()?.message!!)
                Log.d("image2", urlImage2.toString())
                val result: Deferred<Bitmap?> = GlobalScope.async {
                    urlImage2.toBitmap()
                }
                GlobalScope.launch(Dispatchers.Main) {
                    imageView2?.setImageBitmap(result.await())
                }
            }
        })
        //affiche le contenu de main dans le fragment au bous de 3 sec
//        Handler().postDelayed({
//            _binding.hasToDisplay=true;
//        }, 3000)
    }

    fun URL.toBitmap(): Bitmap?{
        return try {
            BitmapFactory.decodeStream(openStream())
        }catch (e: IOException){
            null
        }
    }

}