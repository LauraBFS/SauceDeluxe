package fr.isen.lau.saucedeluxe.categorie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.lau.saucedeluxe.R
import java.lang.String


class CategorieAdapter(context: Context, list: List<Items>) :

    RecyclerView.Adapter<CategorieAdapter.ViewHolder>() {

    private val context: Context
    private val list: List<Items>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(context).inflate(R.layout.categorieplacement, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = list[position]
        /*
        holder.layout.setOnClickListener {
            entryClickListener.invoke(items)
        }
        holder.bind(items)

        holder.textPicture.setText(Items.pictureItem())
        holder.textTitle.setText(String.valueOf(Items.titleItem()))
        holder.textPrice.setText(String.valueOf(Items.priceItem()))*/
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textTitle: TextView
        var textPicture: ImageView
        var textPrice: TextView

        init {
            textTitle = itemView.findViewById(R.id.CategorieName)
            textPicture = itemView.findViewById(R.id.CatagoriesImage)
            textPrice = itemView.findViewById(R.id.CategoriePrice)
        }
    }

    init {
        this.context = context
        this.list = list
    }
}