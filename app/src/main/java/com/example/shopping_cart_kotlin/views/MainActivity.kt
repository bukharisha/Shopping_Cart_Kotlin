package com.example.shopping_cart_kotlin.views

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.shopping_cart_kotlin.R
import com.example.shopping_cart_kotlin.models.CartItem
import com.example.shopping_cart_kotlin.viewmodels.ShopViewModel

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var navController: NavController
    private lateinit var shopViewModel: ShopViewModel
    private var cartQuantity: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Navigation code
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

        shopViewModel = ViewModelProvider(this).get(ShopViewModel::class.java)
        shopViewModel.getCart().observe(this, Observer { cartItems ->
            if (cartItems != null) {
                var quantity = 0
                cartItems.forEach { cartItem ->
                    quantity += cartItem.quantity
                }
                cartQuantity = quantity
                invalidateOptionsMenu()
            }

        })


    }
    //back button
    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        return super.onSupportNavigateUp()
    }
    // Menu code
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val menuItem = menu.findItem(R.id.cartFragment)
        val actionView = menuItem.actionView as View

        val cartBadgeTextView = actionView.findViewById<TextView>(R.id.cart_badge_text_view)

        cartBadgeTextView.text = cartQuantity.toString()
        cartBadgeTextView.visibility = if (cartQuantity == 0) View.GONE else View.VISIBLE
        actionView.setOnClickListener {
            onOptionsItemSelected(menuItem)
        }


        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, navController) ||
                super.onOptionsItemSelected(item)
    }

}