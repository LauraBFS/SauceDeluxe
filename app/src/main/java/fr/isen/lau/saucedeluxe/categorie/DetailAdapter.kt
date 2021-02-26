package fr.isen.lau.saucedeluxe.categorie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.lau.saucedeluxe.R
import fr.isen.lau.saucedeluxe.databinding.ActivityDetailBinding
import fr.isen.lau.saucedeluxe.databinding.CategoriePlacementBinding
import fr.isen.lau.saucedeluxe.model.Item

class DetailAdapter(private val list: List<Item>) { /*


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorieAdapter.ViewHolderDetail {
        val binding = ActivityDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategorieAdapter.ViewHolderDetail(binding)
    }

    override fun onBindViewHolder(holder: CategorieAdapter.ViewHolderDetail, position: Int) {
        holder.textTitleDetail.text = list[position].name
        holder.textPrice.text = list[position].getAffichagePrice()

        if (list[position].getFirstPicture().isNullOrEmpty()) {
            Picasso.get()
                    .load("https://cdn.vox-cdn.com/thumbor/pyedsHD4n0a3uBGicl9o3e580l8=/1400x1400/filters:format(jpeg)/cdn.vox-cdn.com/uploads/chorus_asset/file/18341527/10010.jpeg").into(holder.textPicture)
        } else {
            Picasso.get()
                    .load(list[position].getFirstPicture()).into(holder.textPicture)
        }

        holder.layout.setOnClickListener {
            clickListener.invoke(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    class ViewHolderDetail(binding: ActivityDetailBinding) {
        val textTitleDetail: TextView = itemView.findViewById(R.id.categorieName)
        val textPriceDetail: TextView = itemView.findViewById(R.id.categoriePrice)
        val PictureDetail = binding.catagoriesImage
        val textDescriptionDetail: TextView =  itemView.findViewById(R.id.IngredientDetail)
    }*/
}