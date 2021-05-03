package fr.isen.lau.saucedeluxe.categorie

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import fr.isen.lau.saucedeluxe.R
import fr.isen.lau.saucedeluxe.databinding.ActivityDetailBinding
import fr.isen.lau.saucedeluxe.model.Item

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        val dataItem = intent.getSerializableExtra("items") as? Item
        setContentView(binding.root)

        var sampleImages = arrayOf(dataItem?.images?.get(0))
        val carouselView = findViewById<CarouselView>(R.id.catagoriesImageDetail);

        var imageListener: ImageListener = object : ImageListener {
            override fun setImageForPosition(position: Int, imageView: ImageView) {
                if (dataItem != null) {
                    if(dataItem.images[0].isNullOrEmpty()){
                        Picasso.get().load("https://cdn.vox-cdn.com/thumbor/pyedsHD4n0a3uBGicl9o3e580l8=/1400x1400/filters:format(jpeg)/cdn.vox-cdn.com/uploads/chorus_asset/file/18341527/10010.jpeg").into(imageView)
                    } else {
                        Picasso.get().load(dataItem.images[position]).into(imageView)
                    }
                }
            }
        }
        carouselView.setPageCount(sampleImages.size);
        carouselView.setImageListener(imageListener);

        if (dataItem != null) {
            binding.categorieNameDetail.text = dataItem.name
            binding.ingredientDetail.text = dataItem.getIngredients()
            binding.incrementPrice.text = dataItem.getAffichagePrice()
            binding.catagoriesImageDetail.pageCount = dataItem.images.size
        }

        binding.catagoriesImageDetail.setImageListener(imageListener)

        var quantity = 0
        if (dataItem != null) {
            calculTotal(quantity, dataItem)
        }

        // Bouton + plus
        binding.more.setOnClickListener {
            quantity++
            binding.itemCounter.text = quantity.toString()
            if (dataItem != null) {
                calculTotal(quantity, dataItem)
            }
        }

        // Bouton - moins
        binding.less.setOnClickListener {
            if (quantity > 0)
                quantity--
            binding.itemCounter.text = quantity.toString()
            if (dataItem != null) {
                calculTotal(quantity, dataItem)
            }
        }
    }

    private fun calculTotal(quantity: Int, itemPricedata: Item) {
        val total = quantity * itemPricedata.getPrice()
        "Total : $total €".also {
            binding.incrementPrice.text = it
        }
    }
}