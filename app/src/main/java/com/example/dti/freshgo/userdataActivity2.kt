package com.example.dti.freshgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dti.freshgo.databinding.ActivityUserdata2Binding

class userdataActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityUserdata2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserdata2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.Save.setOnClickListener {
            val intent = Intent(this,freebasicActivity::class.java)
            startActivity(intent)
        }
    }
}