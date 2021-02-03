package fr.isen.lau.saucedeluxe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //Toast.makeText(getApplicationContext(), "Tu cliques sur l'entrée fraté", Toast.LENGTH_SHORT).show();

        val text = "Tu cliques pas ici fraté!"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }
}