package fr.isen.lau.saucedeluxe.categorie

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager

import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import fr.isen.lau.saucedeluxe.HomeActivity
import fr.isen.lau.saucedeluxe.databinding.ActivityCategorieBinding

import org.json.JSONObject
import com.google.gson.GsonBuilder
import fr.isen.lau.saucedeluxe.model.DataResult

enum class ItemType {
    ENTREE, PLAT, DESSERT;

    companion object {
        fun categoryTitle(item: ItemType?) : String {
            return when(item) {
                ENTREE -> "Entree"
                PLAT -> "Plat"
                DESSERT -> "Dessert"
                else -> ""
            }
        }
    }
}

class CategorieActivity : AppCompatActivity(), CategorieAdapter.onItemClickListener {

    private lateinit var binding: ActivityCategorieBinding
    private var category: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCategorieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedItem = intent.getSerializableExtra(HomeActivity.CATEGORY_NAME) as? ItemType
        binding.categorieTitle.text = getCategorieTitle(selectedItem)

        binding.recyclerViewCategorie.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCategorie.adapter = CategorieAdapter(listOf("burger", "Fries", "Pasta", "Pizza", "Tomatoes", "Boeuf bourgigi", "raclette", "tartfiflette"), this)

        getDataItems(getCategorieTitle(selectedItem));
    }

    override fun onItemClick(item: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("items", item)
        startActivity(intent)
    }

    private fun getCategorieTitle(item: ItemType?): String {
        return when (item) {
            ItemType.ENTREE -> "Entrees"
            ItemType.PLAT -> "Plats"
            ItemType.DESSERT -> "Desserts"
            else -> ""
        }
    }

    private fun getDataItems(category: String?) {

        val url = "http://test.api.catering.bluecodegames.com/menu"
        val requestQueue = Volley.newRequestQueue(this)

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Je cherche tes plats la ça se voit pas ?")
        progressDialog.show()

        // Initialize a new RequestQueue instance
        val jsonData = JSONObject().put("id_shop", "1")

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, jsonData,
                {it ->
                    Log.d("Json result", it.toString())
                    val item = Items()
                    item.titleItem(it.getString("titleItem"))
                    //item.pictureItem(it.getInt("PictureItem"))
                    item.priceItem(it.getString("priceItem"))

                    parseResult(it.toString(), category)
                },
                { error ->
                    error.printStackTrace()
                    progressDialog.dismiss()
                }
        )
        requestQueue.add(jsonObjectRequest)
    }

    private fun parseResult(res: String, selectedItem: String?) {
        val menuResult = GsonBuilder().create().fromJson(res, DataResult::class.java)
        val items = menuResult.data.firstOrNull {
            it.name == selectedItem
        }
        loadList(items?.items)
    }

    private fun loadList(items: List<Items>?) {/*
        binding.categorieTitle.isVisible = false

        items?.let {
            val adapter = CategorieAdapter(it) { item ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("items", item)
                startActivity(intent)
            }
            binding.recyclerViewCategorie.layoutManager = LinearLayoutManager(this)
            binding.recyclerViewCategorie.adapter = adapter
        }*/
    }
}