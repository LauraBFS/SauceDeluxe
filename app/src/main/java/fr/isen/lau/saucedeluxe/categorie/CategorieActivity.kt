package fr.isen.lau.saucedeluxe.categorie

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Movie
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import fr.isen.lau.saucedeluxe.HomeActivity
import fr.isen.lau.saucedeluxe.R
import fr.isen.lau.saucedeluxe.databinding.ActivityCategorieBinding
import org.json.JSONArray
import org.json.JSONException


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

    lateinit var mList: RecyclerView
    private var linearLayoutManager: LinearLayoutManager? = null
    private var dividerItemDecoration: DividerItemDecoration? = null
    private var itemsList: List<Items>? = null
    private var adapter: RecyclerView.Adapter<*>? = null
    private val url = "http://test.api.catering.bluecodegames.com/"

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

        // creation de l'intent
        val monIntentRetour : Intent =  Intent(this, HomeActivity::class.java)

        //clic sur le bouton
        Retour.setOnClickListener {
            startActivity(monIntentRetour)
        }

        val actionBar = supportActionBar
        actionBar!!.title = "Choix du roi"
        bindind.categorieTitle.text = getCategorieTitle(selectedItem)

        mList = findViewById(R.id.recyclerView)
        itemsList = ArrayList()
        adapter = CategorieAdapter(applicationContext, itemsList as ArrayList<Items>)
        linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager!!.orientation = LinearLayoutManager.VERTICAL

        dividerItemDecoration = DividerItemDecoration(
            mList.context,
            linearLayoutManager!!.orientation
        )
        mList.setHasFixedSize(true)
        mList.layoutManager = linearLayoutManager
        mList.addItemDecoration(dividerItemDecoration!!)
        mList.adapter = adapter

        getDataItems();
    }

    private fun getCategorieTitle(item: ItemType?): String {
        return when(item) {
            ItemType.ENTREE -> "Entrees"
            ItemType.PLAT -> "Plats"
            ItemType.DESSERT -> "Desserts"
            else -> ""
        }
    }
/*
    override fun onResume() {
        super.onResume()
        Log.d("Tag", "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Tag", "onRestart")
    }

    override fun onDestroy() {
        Log.d("Tag", "onDestroy")
        super.onDestroy()
    }*/

private fun getDataItems() {
    val progressDialog = ProgressDialog(this)
    progressDialog.setMessage("Je cherche tes plats la ça se voit pas ?")
    progressDialog.show()
    val jsonArrayRequest = JsonArrayRequest(url, {
        fun onResponse(response: JSONArray) {
            for (i in 0 until response.length()) {
                try {
                    val jsonObject = response.getJSONObject(i)

                    val item = Items()
                    item.titleItem(jsonObject.getString("titleItem"))
                    item.pictureItem(jsonObject.getInt("PictureItem"))
                    item.priceItem(jsonObject.getString("priceItem"))

                    //itemsList.add(item)
                } catch (e: JSONException) {
                    e.printStackTrace()
                    progressDialog.dismiss()
                }
            }
            adapter!!.notifyDataSetChanged()
            progressDialog.dismiss()
        }
    }, {
        fun onErrorResponse(error: VolleyError) {
            Log.e("Volley", error.toString())
            progressDialog.dismiss()
        }
    })
    val requestQueue = Volley.newRequestQueue(this)
    requestQueue.add(jsonArrayRequest)
}

}


