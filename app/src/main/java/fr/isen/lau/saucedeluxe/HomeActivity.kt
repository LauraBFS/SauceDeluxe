package fr.isen.lau.saucedeluxe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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
            startCategoryActivity(ItemType.ENTREE)
        }

        binding.platsBtn.setOnClickListener {
            startCategoryActivity(ItemType.PLAT)
        }

        binding.dessertsBtn.setOnClickListener {
            startCategoryActivity(ItemType.DESSERT)
        }

        /*
            //initialisation du bouton
            boutonEntrees = findViewById(R.id.btn_home) //button des entrees

            // creation de notre intent
            val monIntent : Intent =  Intent(this, CategorieActivity::class.java)

            //clic sur le bouton
            boutonEntrees.setOnClickListener {
                startActivity(monIntent)
            }*/
    }

    private fun startCategoryActivity(item: ItemType) {
        val intent = Intent(this, CategorieActivity::class.java)
        intent.putExtra(CATEGORY_NAME, item)
        startActivity(intent)
    }

    companion object {
        const val CATEGORY_NAME = "CATEGORY_NAME"
    }
}