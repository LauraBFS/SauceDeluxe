package fr.isen.lau.saucedeluxe.categorie

import android.bluetooth.BluetoothDevice
import android.bluetooth.le.ScanResult
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.lau.saucedeluxe.R
import fr.isen.lau.saucedeluxe.databinding.ActivityDetailBinding
import fr.isen.lau.saucedeluxe.databinding.ActivityDetailBleBinding
import fr.isen.lau.saucedeluxe.model.Item

class DetailBleActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBleBinding.inflate(layoutInflater)

        val listDevice = intent.getSerializableExtra("listDevice")
        setContentView(binding.root)

        if (listDevice != null ){
            binding.textViewdetailBLE.text = listDevice.toString()
        }

    }
}