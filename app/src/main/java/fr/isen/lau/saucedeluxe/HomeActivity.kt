package fr.isen.lau.saucedeluxe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import fr.isen.lau.saucedeluxe.categorie.BleScanActivity
import fr.isen.lau.saucedeluxe.categorie.CategorieActivity
import fr.isen.lau.saucedeluxe.categorie.ItemType
import fr.isen.lau.saucedeluxe.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.entreesBtn.setOnClickListener {
            startCategorieActivity(ItemType.ENTREES)
        }

        binding.platsBtn.setOnClickListener {
            startCategorieActivity(ItemType.PLAT)
        }

        binding.dessertsBtn.setOnClickListener {
            startCategorieActivity(ItemType.DESSERT)
        }

        binding.buttonBLEScan.setOnClickListener {
            val intent = Intent(this, BleScanActivity::class.java)
            startActivity(intent)
        }

    }

    private fun startCategorieActivity(item: ItemType) {
        val intent = Intent(this, CategorieActivity::class.java)
        intent.putExtra(CATEGORY_NAME, item)
        startActivity(intent)
    }

    companion object {
        const val CATEGORY_NAME = "CATEGORY_NAME"
    }
}