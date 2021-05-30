package com.goodboi.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.goodboi.R
import com.goodboi.databinding.MainActivityBinding
import com.goodboi.utils.activityViewBinding

class MainActivity : AppCompatActivity() {
    private val binding by activityViewBinding(MainActivityBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}