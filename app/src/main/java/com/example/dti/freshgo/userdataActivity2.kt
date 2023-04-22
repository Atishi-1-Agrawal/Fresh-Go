package com.example.dti.freshgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.Toast
import com.example.dti.freshgo.databinding.ActivityUserdata2Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class userdataActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityUserdata2Binding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserdata2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()




        binding.Save.setOnClickListener {
            val name = binding.etName.text.toString()
            val contact = binding.etContact.text.toString()
            val dob = binding.etDOB.text.toString()
            val gender = binding.etGender.text.toString()


            database = FirebaseDatabase.getInstance().getReference("Users")
            val User = User(name,contact,dob,gender)
            database.child(name).setValue(User).addOnSuccessListener {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
                .addOnFailureListener {
                Toast.makeText(this,"Something Went Wrong!",Toast.LENGTH_SHORT).show()
            }
        }
    }
}