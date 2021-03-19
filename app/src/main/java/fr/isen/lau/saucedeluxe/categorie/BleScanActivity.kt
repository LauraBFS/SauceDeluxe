package fr.isen.lau.saucedeluxe.categorie

import android.R.attr.x
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.isen.lau.saucedeluxe.R
import fr.isen.lau.saucedeluxe.databinding.ActivityBleScanBinding


class BleScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBleScanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBleScanBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ImageBLEStartPause.setOnClickListener {

        }
    }
}