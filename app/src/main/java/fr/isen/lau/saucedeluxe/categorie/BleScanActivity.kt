package fr.isen.lau.saucedeluxe.categorie

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import fr.isen.lau.saucedeluxe.R
import fr.isen.lau.saucedeluxe.databinding.ActivityBleScanBinding


class BleScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBleScanBinding
    private var isScanning = false
    private val bluetoothAdapter: BluetoothAdapter? = null


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBleScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialisation du Bluetooth adapter.
        val bluetoothManager = getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter: BluetoothAdapter? = bluetoothManager?.adapter


        when {
            !isDeviceHasBLESupport() -> {
                Toast.makeText(this, "Cet appareil n'est pas compatible, sorry", Toast.LENGTH_SHORT).show()
            }
            bluetoothAdapter == null || !bluetoothAdapter.isEnabled -> {
                //je dois activer le bluethooth
            }
            else -> {

            }
        }

        binding.ImageBLEStartPause.setOnClickListener {
            togglePlayPauseAction()
            isDeviceHasBLESupport()
        }
        binding.BleScanTitle.setOnClickListener() {
            togglePlayPauseAction()
        }

    }

    private fun isDeviceHasBLESupport(): Boolean {
        // Use this check to determine whether BLE is supported on the device. Then
        // you can selectively disable BLE-related features.
        if(!packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "Cet appareil n'est pas compatible, sorry", Toast.LENGTH_SHORT).show()
            //finish()
        }
        return packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)
    }

    private fun togglePlayPauseAction() {
        isScanning = !isScanning

        if (isScanning){
            binding.BleScanTitle.text = getString(R.string.ble_scan_pause_title)
            binding.ImageBLEStartPause.setImageResource(R.drawable.a6810)
            binding.progressBarBLE.isVisible = true
            binding.JolieBarBLE.isVisible = false
        }
        else {
            binding.BleScanTitle.text = getString(R.string.ble_scan_play_title)
            binding.ImageBLEStartPause.setImageResource(R.drawable.a6810)
            binding.progressBarBLE.isVisible = false
            binding.JolieBarBLE.isVisible = true
        }
    }



}