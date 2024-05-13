package com.example.shopping_cart_kotlin.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopping_cart_kotlin.models.CartItem
import com.example.shopping_cart_kotlin.models.ProductModel

class CartRepo {
    private val mutableCart: MutableLiveData<List<CartItem>?> = MutableLiveData()
    // 2. TotalPrice code
    private val mutableTotalPrice: MutableLiveData<Double> = MutableLiveData()


    fun getCart(): MutableLiveData<List<CartItem>?> {
        if (mutableCart.value == null) {
            initCart()
        }
        return mutableCart
    }

    fun initCart() {
        mutableCart.value = ArrayList()
        // 9. TotalPrice code
        calculateCartTotal()
    }

    fun addItemToCart(productModel: ProductModel): Boolean {
        if (mutableCart.value == null) {
            initCart()
        }
        val cartItemList = mutableCart.value?.let { ArrayList(it) }

        if (cartItemList != null) {
            for (cartItem in cartItemList) {
                if (cartItem.getProduct().id == productModel.id) {
                    if (cartItem.quantity == 5) {
                        return false
                    }
                    val index = cartItemList.indexOf(cartItem)
                    cartItem.quantity++
                    cartItemList[index] = cartItem
                    mutableCart.value = cartItemList
                    // 8. TotalPrice code
                    calculateCartTotal()
                    return true
                }
            }
        }


        val cartItem = CartItem(productModel, 1)
        cartItemList?.add(cartItem)
        mutableCart.value = cartItemList
        return true
    }

    fun removeItemFromCart(cartItem: CartItem) {
        if (mutableCart.value == null) {
            return
        }

        val cartItemList = ArrayList(mutableCart.value!!)
        cartItemList.remove(cartItem)
        mutableCart.value = cartItemList
        // 7. TotalPrice code
        calculateCartTotal()
    }
    // 4. Spinner quantity code
    fun changeQuantity(cartItem: CartItem, quantity: Int) {
        if (mutableCart.value == null) return

        val cartItemList = ArrayList(mutableCart.value!!)
        val updatedItem = CartItem(cartItem.getProduct(), quantity)
        cartItemList[cartItemList.indexOf(cartItem)] = updatedItem
        mutableCart.value = cartItemList
        // 6. TotalPrice code
        calculateCartTotal()
    }

    // 5. TotalPrice code
    private fun calculateCartTotal() {
        mutableCart.value?.let { cartItemList ->
            var total = 0.0
            for (cartItem in cartItemList) {
                total += cartItem.getProduct().price * cartItem.quantity
            }
            mutableTotalPrice.value = total
        }
    }

    // 3. TotalPrice code
    fun getTotalPrice(): LiveData<Double> {
        if (mutableTotalPrice.value == null) {
            mutableTotalPrice.value = 0.0
        }
        return mutableTotalPrice
    }


}