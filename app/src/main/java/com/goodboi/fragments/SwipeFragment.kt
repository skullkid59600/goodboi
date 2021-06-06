package com.goodboi.fragments

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.goodboi.activities.data.Dog
import com.goodboi.activities.data.ImageViewModelFactory
import com.goodboi.activities.data.Repository.ImageRepository
import com.goodboi.databinding.FragmentSwipeBinding
import com.goodboi.fragments.viewModel.ImageViewModel
import com.goodboi.fragments.viewModel.myResponse
import com.goodboi.utils.fragmentAutoCleared
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SwipeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SwipeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentSwipeBinding by fragmentAutoCleared()
    //Implement API Images
    private lateinit var imageViewModel: ImageViewModel
    var dogs: ArrayList<Dog?> = ArrayList<Dog?>()

    fun makeImageRequest() : String{
        val repository = ImageRepository()
        val viewModelFactory = ImageViewModelFactory(repository)
        var res : String = "aa"
        imageViewModel = ViewModelProvider(this, viewModelFactory).get(ImageViewModel::class.java)
        imageViewModel.getImage()
        myResponse.observe(viewLifecycleOwner, Observer { response ->
            if(response.isSuccessful){
                Log.d("Response", response.body()?.message!!)
                Log.d("Response", response.body()?.status!!)
                res = response.body()?.message!!
            }else{
                Log.d("Response", response.errorBody().toString())
                res = response.errorBody().toString()
            }
        })
        println(res)
        return res
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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
        for (i in 0..9) {
            Handler().postDelayed({
                dogs.add(Dog(i, makeImageRequest()))
                println(dogs.get(i).toString())
            }, 3000)
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SwipeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SwipeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}