package fr.isen.lau.saucedeluxe.categorie

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo.getCategoryTitle
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.provider.FontsContractCompat.resetCache
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isen.lau.saucedeluxe.HomeActivity
import fr.isen.lau.saucedeluxe.R
import fr.isen.lau.saucedeluxe.databinding.ActivityCategorieBinding
import org.json.JSONObject

enum class ItemType {
    ENTREE, PLAT, DESSERT;

    companion object {
        fun categoryTitle(item: ItemType?) : String {
            return when(item) {
                ENTREE -> "Entrees"
                PLAT -> "Plats"
                DESSERT -> "Desserts"
                else -> ""
            }
        }
    }
}

class CategorieActivity : AppCompatActivity() {

    //Declaration de mon bouton
    lateinit var Retour : Button
    private lateinit var bindind: ActivityCategorieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivityCategorieBinding.inflate(layoutInflater)
        setContentView(bindind.root)

        val selectedItem = intent.getSerializableExtra(HomeActivity.CATEGORY_NAME) as? ItemType

        //initialisation
        Retour = findViewById(R.id.return_btn)

        // creation de notre intent
        val monIntentRetour : Intent =  Intent(this,HomeActivity::class.java)

        //clic sur le bouton
        Retour.setOnClickListener {
            startActivity(monIntentRetour)
        }

        val actionBar = supportActionBar
        actionBar!!.title = "Choix du roi"
        bindind.categorieTitle.text = getCategorieTitle(selectedItem)
    }

    private fun getCategorieTitle(item: ItemType?): String {
        return when(item) {
            ItemType.ENTREE -> "Entrees"
            ItemType.PLAT -> "Plats"
            ItemType.DESSERT -> "Desserts"
            else -> ""
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("Tag", "onResume est lancé")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Tag", "onRestart est lancé")
    }

    override fun onDestroy() {
        Log.d("Tag", "onDestroy est lancé")
        super.onDestroy()
    }

}