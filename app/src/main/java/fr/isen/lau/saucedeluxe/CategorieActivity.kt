package fr.isen.lau.saucedeluxe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CategorieActivity : AppCompatActivity() {

    //Declaration de mon bouton
    lateinit var Retour : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorie)

        //initialisation
        Retour = findViewById(R.id.return_btn)

        // creation de notre intent
        val monIntentRetour : Intent =  Intent(this,HomeActivity::class.java)

        //clic sur le bouton
        Retour.setOnClickListener {
            startActivity(monIntentRetour)
        }
    }

}