package com.example.shopping_cart_kotlin.views

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.shopping_cart_kotlin.R
import com.example.shopping_cart_kotlin.adapters.CartListAdapter
import com.example.shopping_cart_kotlin.databinding.FragmentCartBinding
import com.example.shopping_cart_kotlin.models.CartItem
import com.example.shopping_cart_kotlin.viewmodels.ShopViewModel

class CartFragment : Fragment(), CartListAdapter.CartInterface {

    private val TAG = "CartFragment"
    private lateinit var shopViewModel: ShopViewModel
    private lateinit var fragmentCartBinding: FragmentCartBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentCartBinding = FragmentCartBinding.inflate(inflater, container, false)
        return fragmentCartBinding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(requireView())


        val cartListAdapter = CartListAdapter(this)
        fragmentCartBinding.cartRecyclerView.adapter = cartListAdapter
        fragmentCartBinding.cartRecyclerView.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )

        shopViewModel = ViewModelProvider(requireActivity()).get(ShopViewModel::class.java)
        shopViewModel.getCart().observe(viewLifecycleOwner) { cartItems ->
            cartListAdapter.submitList(cartItems)
            if (cartItems != null) {
                fragmentCartBinding.placeOrderButton.isEnabled = cartItems.size > 0
            }

        }
        // 4. TotalPrice code
        shopViewModel.getTotalPrice().observe(viewLifecycleOwner, Observer { totalPrice ->
            fragmentCartBinding.oderTotalTextView.text = "Total: $$totalPrice"
        })

        fragmentCartBinding.placeOrderButton.setOnClickListener {
            navController.navigate(R.id.action_cartFragment_to_orderFragment)
        }


    }

    override fun deleteItem(cartItem: CartItem) {
        shopViewModel.removeItemFromCart(cartItem)
    }

    override fun changeQuantity(cartItem: CartItem, quantity: Int) {
        shopViewModel.changeQuantity(cartItem, quantity)
    }

}