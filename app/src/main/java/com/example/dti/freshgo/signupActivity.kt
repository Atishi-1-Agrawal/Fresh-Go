package com.example.dti.freshgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.example.dti.freshgo.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class signupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.textView.setOnClickListener {
            val intent = Intent(this, signinActivity::class.java)
            startActivity(intent)
        }

        firebaseAuth= FirebaseAuth.getInstance()



        binding.button.setOnClickListener{
            val email = binding.emailEt.text.toString()
            //for password
            val pass = binding.passET.text.toString()
            //for confirm password
            val confPass = binding.confirmPassEt.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && confPass.isNotEmpty()){
                if(pass == confPass){
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if(it.isSuccessful){
                            val intent = Intent(this,signinActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else{
                    Toast.makeText(this,"Confirm Password and Password is Not Same!",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"Fields Cannot Be Empty!",Toast.LENGTH_SHORT).show()
            }
        }
    }
}