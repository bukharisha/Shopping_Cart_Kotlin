package com.example.shopping_cart_kotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping_cart_kotlin.databinding.CartRowBinding
import com.example.shopping_cart_kotlin.models.CartItem

class CartListAdapter(private var cartInterface: CartInterface) :
    ListAdapter<CartItem, CartListAdapter.CartVH>(CartItem.itemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cartRowBinding = CartRowBinding.inflate(layoutInflater, parent, false)
        return CartVH(cartRowBinding)
    }

    override fun onBindViewHolder(holder: CartVH, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CartVH(private val cartRowBinding: CartRowBinding) : RecyclerView.ViewHolder(cartRowBinding.root) {

        init {
            cartRowBinding.deleteProductButton.setOnClickListener {
                cartInterface.deleteItem(getItem(bindingAdapterPosition))
            }

            cartRowBinding.quantitySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val quantity = position + 1
                    if (quantity == getItem(bindingAdapterPosition).quantity) {
                        return
                    }
                    cartInterface.changeQuantity(getItem(bindingAdapterPosition), quantity)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }

        fun bind(cartItem: CartItem) {
            cartRowBinding.cartItem = cartItem
            cartRowBinding.executePendingBindings()
        }
    }

    interface CartInterface {
        fun deleteItem(cartItem: CartItem)
        fun changeQuantity(cartItem: CartItem, quantity: Int)
    }
}
