package fr.isen.lau.saucedeluxe.categorie

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

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

class CategorieActivity : AppCompatActivity(), CategorieAdapter.onItemClickListener {

    private var url = "http://test.api.catering.bluecodegames.com/menu"

    private lateinit var binding: ActivityCategorieBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCategorieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedItem = intent.getSerializableExtra(HomeActivity.CATEGORY_NAME) as? ItemType
        binding.categorieTitle.text = getCategorieTitle(selectedItem)

        binding.recyclerViewCategorie.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCategorie.adapter = CategorieAdapter(listOf("burger", "Fries", "Pasta", "Pizza", "Tomatoes", "Boeuf bourgigi", "raclette", "tartfiflette"), this)

        //getDataItems();
    }

    override fun onItemClick(item: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("items", item)
        startActivity(intent)
    }
    private fun getCategorieTitle(item: ItemType?): String {
        return when(item) {
            ItemType.ENTREE -> "Entrees"
            ItemType.PLAT -> "Plats"
            ItemType.DESSERT -> "Desserts"
            else -> ""
        }
    }

    private fun getDataItems() {

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Je cherche tes plats la ça se voit pas ?")
        progressDialog.show()

        // Initialize a new RequestQueue instance
        val jsonData = JSONObject().put("id_shop", "1")

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, jsonData,
                {
                    val item = Items()
                    item.titleItem(it.getString("titleItem"))
                    //item.pictureItem(it.getInt("PictureItem"))
                    item.priceItem(it.getString("priceItem"))
                },
                { error ->
                    error.printStackTrace()
                    progressDialog.dismiss()
                }
        )
            val requestQueue = Volley.newRequestQueue(this)
            requestQueue.add(jsonObjectRequest)
    }

}