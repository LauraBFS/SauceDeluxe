package fr.isen.lau.saucedeluxe.categorie

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategorieAdapter(){


    class CategoriesViewHolder(dishesBinding: DishesCellBinding): RecyclerView.ViewHolder(dishesBinding.root) {
        val titleView: TextView = dishesBinding.dishesTitle
        val priceView: TextView = dishesBinding.dishPrice
        val imageView: ImageView = dishesBinding.dishImageView
        val layout = dishesBinding.root

        fun bind(dish: Dish) {
            titleView.text = dish.name
            priceView.text = "${dish.prices.first().price} €" // dish.prices.first().price + " €"
            Picasso.get()
                    .load(dish.getThumbnailUrl())
                    .placeholder(R.drawable.android_logo_restaurant)
                    .into(imageView)
        }
    }
}