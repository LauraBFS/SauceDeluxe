package fr.isen.lau.saucedeluxe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeActivity : AppCompatActivity() {
    //Declaration de mes boutons
    lateinit var boutonEntrees : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

            //initialisation du bouton
            boutonEntrees = findViewById(R.id.btn_home) //button des entrees

            // creation de notre intent
            val monIntent : Intent =  Intent(this,CategorieActivity::class.java)

            //clic sur le bouton
            boutonEntrees.setOnClickListener {
                startActivity(monIntent)
            }
    }
}