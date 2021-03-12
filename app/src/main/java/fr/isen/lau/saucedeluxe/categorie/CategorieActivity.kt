package fr.isen.lau.saucedeluxe.categorie

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity

import androidx.recyclerview.widget.LinearLayoutManager

import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

import fr.isen.lau.saucedeluxe.HomeActivity
import fr.isen.lau.saucedeluxe.databinding.ActivityCategorieBinding

import org.json.JSONObject
import com.google.gson.GsonBuilder
import fr.isen.lau.saucedeluxe.HomeActivity.Companion.CATEGORY_NAME
import fr.isen.lau.saucedeluxe.model.Category
import fr.isen.lau.saucedeluxe.model.DataResult
import fr.isen.lau.saucedeluxe.model.Item

enum class ItemType {
    ENTREES, PLAT, DESSERT;

    companion object {
        fun categoryTitle(item: ItemType?) : String {
            return when(item) {
                ENTREES -> "Entrées"
                PLAT -> "Plat"
                DESSERT -> "Dessert"
                else -> ""
            }
        }
    }
}

class CategorieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategorieBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCategorieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedItem = intent.getSerializableExtra(HomeActivity.CATEGORY_NAME) as? ItemType
        binding.categorieTitle.text = getCategorieTitle(selectedItem)

        binding.recyclerViewCategorie.layoutManager = LinearLayoutManager(this)

        getDataItems(getCategorieTitle(selectedItem));
    }

    /*private fun ItemDisplay(itemData: List<Item>) {
        binding.recyclerViewCategorie.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCategorie.adapter = CategorieAdapter(itemData) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("items", it)
            startActivity(intent)
        }
    }*/

    private fun getDataItems(category: String?) {

        val url = "http://test.api.catering.bluecodegames.com/menu"
        val requestQueue = Volley.newRequestQueue(this)

        // Initialisation de la RequestQueue instance
        val jsonData = JSONObject().put("id_shop", 1)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, jsonData,
                {it ->
                    Log.d("Json result", it.toString())
                    parseResult(it.toString(), category)
                },
                { error ->
                    error.printStackTrace()
                }
        )
        requestQueue.add(jsonObjectRequest)
    }

    private fun parseResult(res: String, selectedItem: String?) {
        val menuResult = GsonBuilder().create().fromJson(res, DataResult::class.java)
        val items = menuResult.data.firstOrNull { it.name == selectedItem }
        loadList(items?.items)
    }

    private fun loadList(items: List<Item>?) {

        items?.let {
            val adapter = CategorieAdapter(it) { item ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("items", item)
                startActivity(intent)
            }
            binding.recyclerViewCategorie.layoutManager = LinearLayoutManager(this)
            binding.recyclerViewCategorie.adapter = adapter
        }
    }

    private fun getCategorieTitle(item: ItemType?): String {
        return when (item) {
            ItemType.ENTREES -> "Entrées"
            ItemType.PLAT -> "Plats"
            ItemType.DESSERT -> "Desserts"
            else -> ""
        }
    }
}