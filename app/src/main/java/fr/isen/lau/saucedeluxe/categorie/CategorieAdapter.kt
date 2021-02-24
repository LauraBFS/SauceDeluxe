package fr.isen.lau.saucedeluxe.categorie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.lau.saucedeluxe.R
import fr.isen.lau.saucedeluxe.databinding.CategoriePlacementBinding


class CategorieAdapter(private val list: List<String>, private val clickListener: onItemClickListener) : RecyclerView.Adapter<CategorieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategoriePlacementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textTitle.text = list[position]
        //holder.textPicture.picasso faudra utiliser la lib picasso
        //holder.textPrice.text = item.priceItem

        holder.layout.setOnClickListener {
            clickListener.onItemClick(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView = itemView.findViewById(R.id.categorieName)
        val textPrice: TextView = itemView.findViewById(R.id.categoriePrice)
        //val textPicture: ImageView = itemView.findViewById(R.id.CatagoriesImage)
        val layout = itemView.findViewById<View>(R.id.cellLayout)
    }

    interface onItemClickListener {
        fun onItemClick(item: String)
    }
}