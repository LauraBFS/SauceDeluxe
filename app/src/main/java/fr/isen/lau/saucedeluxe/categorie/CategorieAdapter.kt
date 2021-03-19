package fr.isen.lau.saucedeluxe.categorie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.lau.saucedeluxe.R
import fr.isen.lau.saucedeluxe.model.Item
import com.squareup.picasso.Picasso
import fr.isen.lau.saucedeluxe.databinding.CategoriePlacementBinding

class CategorieAdapter(private val list: List<Item>,
                       private val clickListener: (Item) -> Unit) : RecyclerView.Adapter<CategorieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategoriePlacementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textTitle.text = list[position].name
        holder.textPrice.text = list[position].getAffichagePrice()

        if (list[position].getFirstPicture().isNullOrEmpty()) {
            Picasso.get()
                    .load("https://cdn.vox-cdn.com/thumbor/pyedsHD4n0a3uBGicl9o3e580l8=/1400x1400/filters:format(jpeg)/cdn.vox-cdn.com/uploads/chorus_asset/file/18341527/10010.jpeg")
                    .into(holder.Picture)
        } else {
            Picasso.get()
                    .load(list[position].getFirstPicture())
                    .into(holder.Picture)
        }

        holder.layout.setOnClickListener {
            clickListener.invoke(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(binding: CategoriePlacementBinding) : RecyclerView.ViewHolder(binding.root) {
        val textTitle: TextView = itemView.findViewById(R.id.TitleDish)
        val textPrice: TextView = itemView.findViewById(R.id.categoriePrice)
        val Picture = binding.catagoriesImage
        val layout = itemView.findViewById<View>(R.id.CellLayout)
    }
}