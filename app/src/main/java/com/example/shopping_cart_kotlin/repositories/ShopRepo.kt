package com.example.shopping_cart_kotlin.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopping_cart_kotlin.models.ProductModel
import java.util.UUID

class ShopRepo {
    private var mutableProductList: MutableLiveData<List<ProductModel>>? = null

    fun getProducts(): LiveData<List<ProductModel>> {
        if (mutableProductList == null) {
            mutableProductList = MutableLiveData()
            loadProducts()
        }
        return mutableProductList!!
    }

    private fun loadProducts() {
        val productList = mutableListOf<ProductModel>()
        productList.add(
            ProductModel(
                UUID.randomUUID().toString(),
                "iMac 21",
                1299.0,
                true,
                "https://firebasestorage.googleapis.com/v0/b/fly-cart-kotlin.appspot.com/o/i%20Mac%2021.png?alt=media&token=317465b7-faff-490d-9803-e78abbdc493c"
            )
        )
        productList.add(
            ProductModel(
                UUID.randomUUID().toString(),
                "Hot 10",
                300.0,
                true,
                "https://firebasestorage.googleapis.com/v0/b/fly-cart-kotlin.appspot.com/o/hot10.jpg?alt=media&token=ccc8c203-4274-437d-9c70-7473d1f884a4"
            )
        )
        productList.add(
            ProductModel(
                UUID.randomUUID().toString(),
                "Honor 9x",
                299.0,
                false,
                "https://firebasestorage.googleapis.com/v0/b/fly-cart-kotlin.appspot.com/o/honor9x.png?alt=media&token=45bf3f69-6166-4212-a431-1701b0d83350"
            )
        )
        mutableProductList!!.value = productList
    }
}