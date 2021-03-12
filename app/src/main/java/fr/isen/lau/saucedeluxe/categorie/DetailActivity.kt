package fr.isen.lau.saucedeluxe.categorie

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import fr.isen.lau.saucedeluxe.HomeActivity
import fr.isen.lau.saucedeluxe.R
import fr.isen.lau.saucedeluxe.databinding.ActivityDetailBinding
import fr.isen.lau.saucedeluxe.model.Item
import fr.isen.lau.saucedeluxe.model.Price

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        val dataItem = intent.getSerializableExtra("items") as? Item
        setContentView(binding.root)

        if (dataItem != null) {
            binding.categorieNameDetail.text = dataItem.name
            binding.ingredientDetail.text = dataItem.getIngredients()
            binding.incrementPrice.text = dataItem.getAffichagePrice()

            if (dataItem.getFirstPicture().isNullOrEmpty()) {
                Picasso.get()
                        .load("https://cdn.vox-cdn.com/thumbor/pyedsHD4n0a3uBGicl9o3e580l8=/1400x1400/filters:format(jpeg)/cdn.vox-cdn.com/uploads/chorus_asset/file/18341527/10010.jpeg")
                        .into(binding.catagoriesImageDetail)
            } else {
                Picasso.get()
                        .load(dataItem.getFirstPicture())
                        .into(binding.catagoriesImageDetail)
            }
        }

        var quantity = 0

        // Bouton + plus
        binding.more.setOnClickListener {
            quantity++
            binding.itemCounter.text = quantity.toString()
        }

        // Bouton - moins
        binding.less.setOnClickListener {
            if (quantity > 0)
                quantity--
            binding.itemCounter.text = quantity.toString()
        }
    }
}