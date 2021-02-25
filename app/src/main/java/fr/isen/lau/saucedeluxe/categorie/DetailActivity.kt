package fr.isen.lau.saucedeluxe.categorie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.isen.lau.saucedeluxe.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}