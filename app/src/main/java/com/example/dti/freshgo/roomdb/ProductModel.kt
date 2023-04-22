package com.example.dti.freshgo.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "products")
data class ProductModel(
    @PrimaryKey
    @Nonnull
    val productId: String,
    @ColumnInfo("productName")
    val productName: String? = "",
    @ColumnInfo("productImage")
    val productImage: String? = "",
    @ColumnInfo("productSp") 
    val productSp: String? = "",
)
