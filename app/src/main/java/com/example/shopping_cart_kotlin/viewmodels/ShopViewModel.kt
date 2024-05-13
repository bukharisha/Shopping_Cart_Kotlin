package com.example.shopping_cart_kotlin.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopping_cart_kotlin.models.CartItem
import com.example.shopping_cart_kotlin.models.ProductModel
import com.example.shopping_cart_kotlin.repositories.CartRepo
import com.example.shopping_cart_kotlin.repositories.ShopRepo

class ShopViewModel : ViewModel() {

    private val shopRepo: ShopRepo = ShopRepo()
    private val cartRepo: CartRepo = CartRepo()

    //MutableLiveData
    private val mutableProduct: MutableLiveData<ProductModel> = MutableLiveData()


    fun getProducts(): LiveData<List<ProductModel>> {
        return shopRepo.getProducts()
    }
    //mutableProduct //MutableLiveData
    fun setProduct(productModel: ProductModel) {// detail product layout k liye
        mutableProduct.value = productModel
    }
    fun getProduct(): LiveData<ProductModel> { // detail product layout k liye
        return mutableProduct
    }
    fun getCart(): MutableLiveData<List<CartItem>?> { // cartRepo se getCart ka code add howa hai
        return cartRepo.getCart()
    }
    fun addItemToCart(productModel: ProductModel): Boolean {
        return cartRepo.addItemToCart(productModel)
    }
    fun removeItemFromCart(cartItem: CartItem) {
        cartRepo.removeItemFromCart(cartItem)
    }
    // 3. Spinner quantity code
    fun changeQuantity(cartItem: CartItem, quantity: Int) {
        cartRepo.changeQuantity(cartItem, quantity)
    }
    // 1. TotalPrice code
    fun getTotalPrice(): LiveData<Double> {
        return cartRepo.getTotalPrice()
    }

    fun resetCart() {
        cartRepo.initCart()
    }


}