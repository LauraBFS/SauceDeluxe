package fr.isen.lau.saucedeluxe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import fr.isen.lau.saucedeluxe.categorie.CategorieActivity
import fr.isen.lau.saucedeluxe.categorie.ItemType
import fr.isen.lau.saucedeluxe.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    //Declaration de mes boutons
    //lateinit var boutonEntrees : Button

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_home)

        binding.entreesBtn.setOnClickListener {
            startCategorieActivity(ItemType.ENTREE)
        }

        binding.platsBtn.setOnClickListener {
            startCategorieActivity(ItemType.PLAT)
        }

        binding.dessertsBtn.setOnClickListener {
            startCategorieActivity(ItemType.DESSERT)
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