package fr.isen.lau.saucedeluxe.categorie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import fr.isen.lau.saucedeluxe.R
import kotlin.math.max

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        Toast.makeText(this, intent.getStringExtra("items") ?: "", Toast.LENGTH_LONG).show()
    }
}