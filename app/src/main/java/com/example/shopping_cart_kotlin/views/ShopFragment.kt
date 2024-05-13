package com.example.shopping_cart_kotlin.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.shopping_cart_kotlin.R
import com.example.shopping_cart_kotlin.adapters.ShopListAdapter
import com.example.shopping_cart_kotlin.databinding.FragmentShopBinding
import com.example.shopping_cart_kotlin.models.ProductModel
import com.example.shopping_cart_kotlin.viewmodels.ShopViewModel
import com.google.android.material.snackbar.Snackbar

class ShopFragment : Fragment(), ShopListAdapter.ShopInterface {

    private lateinit var fragmentShopBinding: FragmentShopBinding
    private lateinit var shopListAdapter: ShopListAdapter
    private lateinit var shopViewModel: ShopViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentShopBinding = FragmentShopBinding.inflate(inflater, container, false)
        return fragmentShopBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shopListAdapter = ShopListAdapter(this)
        fragmentShopBinding.shopRecyclerView.adapter = shopListAdapter

        // Item Decoration
        fragmentShopBinding.shopRecyclerView.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )
        fragmentShopBinding.shopRecyclerView.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL)
        )

        shopViewModel = ViewModelProvider(requireActivity()).get(ShopViewModel::class.java)
        shopViewModel.getProducts().observe(viewLifecycleOwner, Observer { products ->
            shopListAdapter.submitList(products)
        })

        navController = Navigation.findNavController(view)
    }

    override fun addItem(productModel: ProductModel) {
        val isAdded = shopViewModel.addItemToCart(productModel)
        val message = if (isAdded) "${productModel.name} added to cart." else "Already have the max quantity in cart."
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
            .setAction("Checkout") {
                navController.navigate(R.id.action_shopFragment_to_cartFragment)
            }
            .show()
    }

    override fun onItemClick(productModel: ProductModel) {
        shopViewModel.setProduct(productModel)
        navController.navigate(R.id.action_shopFragment_to_productDetailFragment)
    }
}
