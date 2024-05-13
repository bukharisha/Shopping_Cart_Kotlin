package com.example.shopping_cart_kotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping_cart_kotlin.databinding.ShopRowBinding
import com.example.shopping_cart_kotlin.models.ProductModel

class ShopListAdapter(private val shopInterface: ShopInterface) : ListAdapter<ProductModel, ShopListAdapter.ShopViewHolder>(ProductModel.itemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val shopRowBinding = ShopRowBinding.inflate(layoutInflater, parent, false)
        shopRowBinding.shopInterface = shopInterface
        return ShopViewHolder(shopRowBinding)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
    }

    inner class ShopViewHolder(private val shopRowBinding: ShopRowBinding) : RecyclerView.ViewHolder(shopRowBinding.root) {
        fun bind(product: ProductModel) {
            shopRowBinding.productModel = product
            shopRowBinding.executePendingBindings()
        }
    }

    interface ShopInterface {
        fun addItem(productModel: ProductModel)
        fun onItemClick(productModel: ProductModel)
    }
}
