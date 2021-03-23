package fr.isen.lau.saucedeluxe.categorie

import android.bluetooth.BluetoothDevice
import android.bluetooth.le.ScanRecord
import android.bluetooth.le.ScanResult
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import fr.isen.lau.saucedeluxe.R
import fr.isen.lau.saucedeluxe.databinding.ActivityDetailBinding
import fr.isen.lau.saucedeluxe.databinding.ActivityDetailBleBinding
import fr.isen.lau.saucedeluxe.model.Item
import java.io.Serializable

class DetailBleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBleBinding
    private lateinit var listDevice : BluetoothDevice

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE) ?: error("Manque le bluetooth device de l'activity d'avant!")

        binding.nameDeviceBLE.text = listDevice?.name
        binding.adressBleDetail.text = listDevice?.address

        //binding.StatusBLE.text = listDevice?.scanRecord?.advertiseFlags.toString()
        //binding.adressBleDetail.text = listDevice?.scanRecord?.deviceName.toString()

    }
}