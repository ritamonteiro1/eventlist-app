package com.example.featurehome.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.featurehome.R
import com.example.featurehome.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.homeFragmentContainer) as NavHostFragment
    }

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, HomeActivity::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.homeFragmentContainer)
        return navController.navigateUp()
                || super.onSupportNavigateUp()
    }
}