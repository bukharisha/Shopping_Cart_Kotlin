package com.example.shopping_cart_kotlin.models

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide

class ProductModel (
    var id: String,
    var name: String,
    var price: Double,
    var isAvailable: Boolean,
    var imageUrl: String

){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProductModel

        if (id != other.id) return false
        if (name != other.name) return false
        if (price != other.price) return false
        if (isAvailable != other.isAvailable) return false
        if (imageUrl != other.imageUrl) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + isAvailable.hashCode()
        result = 31 * result + imageUrl.hashCode()
        return result
    }

    override fun toString(): String {
        return "ProductModel(id='$id', name='$name', price=$price, isAvailable=$isAvailable, imageUrl='$imageUrl')"
    }

    companion object {
        val itemCallback = object : DiffUtil.ItemCallback<ProductModel>() {
            override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    object BindingAdapters {
        @JvmStatic
        @BindingAdapter("android:productImage")
        fun loadImage(imageView: ImageView, imageUrl: String?) {
            imageUrl?.let {
                Glide.with(imageView)
                    .load(it)
                    .fitCenter()
                    .into(imageView)
            }
        }
    }

}