package com.example.shopping_cart_kotlin.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.shopping_cart_kotlin.R
import com.example.shopping_cart_kotlin.databinding.FragmentProductDetailBinding
import com.example.shopping_cart_kotlin.viewmodels.ShopViewModel


class ProductDetailFragment : Fragment() {

    private lateinit var fragmentProductDetailBinding: FragmentProductDetailBinding
    private lateinit var shopViewModel: ShopViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentProductDetailBinding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return fragmentProductDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shopViewModel = ViewModelProvider(requireActivity()).get(ShopViewModel::class.java)
        fragmentProductDetailBinding.apply {
            shopViewModel = this@ProductDetailFragment.shopViewModel
        }
    }

}