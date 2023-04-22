package com.example.dti.freshgo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView.ScaleType
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.dti.freshgo.MainActivity
import com.example.dti.freshgo.R
import com.example.dti.freshgo.databinding.ActivityProductDetailsBinding
import com.example.dti.freshgo.roomdb.AppDatabase
import com.example.dti.freshgo.roomdb.ProductDao
import com.example.dti.freshgo.roomdb.ProductModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)

        getProductDetails(intent.getStringExtra("id"))

        setContentView(binding.root)
    }

    private fun getProductDetails(proId: String?){
        Firebase.firestore.collection("products")
            .document(proId!!).get().addOnSuccessListener {
                val list = it.get("productImages") as ArrayList<String>
                val name = it.getString("productName")
                val productSp = it.getString("productSp")
                val productDes = it.getString("productDescription")

                binding.textView7.text = name
                binding.textView8.text = productSp
                binding.textView9.text = productDes

                val slidList = ArrayList<SlideModel>()
                for(data in list){
                    slidList.add(SlideModel(data, ScaleTypes.FIT))
                }

                cartAction(proId, name,productSp, it.getString("productCoverImg "))

                binding.imageSlider.setImageList(slidList)

            }.addOnFailureListener {
                Toast.makeText(this,"Something Went Wrong!",Toast.LENGTH_SHORT).show()
            }
    }

    private fun cartAction(proId: String, name: String?, productSp: String?, coverImg: String?) {

        val productDao = AppDatabase.getInstance(this).productDao()


        if(productDao.isExit(proId) != null){
            binding.textView10.text = "Go To Cart"
        }else{
            binding.textView10.text = "Add To Cart"
        }

        binding.textView10.setOnClickListener {
            if(productDao.isExit(proId) != null){
                openCart()
            }else{
                addToCart(productDao, proId, name, productSp, coverImg)
            }
        }
    }

    private fun addToCart(productDao: ProductDao, proId: String, name: String?, productSp: String?, coverImg: String?) {
        val data = ProductModel(proId, name, coverImg, productSp)
        lifecycleScope.launch(Dispatchers.IO){
            productDao.insertProduct(data)
            binding.textView10.text = "Go To Cart"

        }
    }

    private fun openCart() {
        val preference = this.getSharedPreferences("info", MODE_PRIVATE)
        val editor = preference.edit()
        editor.putBoolean("isCart", true)
        editor.apply()


        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}