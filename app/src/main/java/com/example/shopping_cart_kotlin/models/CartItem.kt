package com.example.shopping_cart_kotlin.models

import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil

class CartItem(private var productModel: ProductModel, var quantity: Int) {

    fun getProduct(): ProductModel {
        return productModel
    }

    fun setProduct(productModel: ProductModel) {
        this.productModel = productModel
    }

    override fun toString(): String {
        return "CartItem(productModel=$productModel, quantity=$quantity)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CartItem

        if (productModel != other.productModel) return false
        if (quantity != other.quantity) return false

        return true
    }

    override fun hashCode(): Int {
        var result = productModel.hashCode()
        result = 31 * result + quantity
        return result
    }

    companion object {

        @JvmStatic
        @BindingAdapter("android:setVal")
        fun getSelectedSpinnerValue(spinner: Spinner, quantity: Int) {
            spinner.setSelection(quantity - 1, true)
        }

        val itemCallback = object : DiffUtil.ItemCallback<CartItem>() {
            override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
                return oldItem.quantity == newItem.quantity
            }

            override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}