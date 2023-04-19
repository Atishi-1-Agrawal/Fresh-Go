package com.example.dti.freshgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dti.freshgo.databinding.ActivityAboutusBinding

class aboutusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutusBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAboutusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.skip.setOnClickListener{
            val intent = Intent(this,userdataActivity2::class.java)
            startActivity(intent)
        }
    }
}