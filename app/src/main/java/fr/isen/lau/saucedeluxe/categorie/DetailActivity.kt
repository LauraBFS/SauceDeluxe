package fr.isen.lau.saucedeluxe.categorie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.isen.lau.saucedeluxe.HomeActivity
import fr.isen.lau.saucedeluxe.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedItem = intent.getSerializableExtra(HomeActivity.CATEGORY_NAME) as? ItemType

       /* item.getAllPictures()?.let {
            binding.viewPager.adapter = DetailAdapter(this, it)*/

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