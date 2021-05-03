package fr.isen.lau.saucedeluxe.categorie

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import fr.isen.lau.saucedeluxe.R
import fr.isen.lau.saucedeluxe.databinding.ActivityBleScanBinding
import android.Manifest
import android.app.SearchManager
import android.bluetooth.BluetoothDevice
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BleScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBleScanBinding
    private var isScanning = false
    private var bluetoothAdapter: BluetoothAdapter? = null
    private var bluetoothLeScanner: BluetoothLeScanner? = null
    private var scanning = false
    private val handler = Handler()

    private var leDeviceListAdapter: BLEScanAdapter? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBleScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialisation du Bluetooth adapter.
        bluetoothAdapter = getSystemService(BluetoothManager::class.java)?.adapter

        startBLEIfPossible()
        isDeviceHasBLESupport()

        binding.ImageBLEStartPause.setOnClickListener {
            togglePlayPauseAction()
            isDeviceHasBLESupport()
        }
        binding.BleScanTitle.setOnClickListener() {
            togglePlayPauseAction()
        }
    }

    private fun startBLEIfPossible() {

        when {
            !isDeviceHasBLESupport() || bluetoothAdapter == null -> {
                Toast.makeText(this, "Cet appareil n'est pas compatible, sorry", Toast.LENGTH_SHORT)
                    .show()
            }
            !(bluetoothAdapter?.isEnabled ?: false) -> {
                //je dois activer le bluethooth
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
            }
            ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED -> {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_PERMISSION_LOCATION)
            }
            else -> {
                //youpi, on peut faire du BLE des alpes
                Log.d("ScanDevices", "onRequestPermissionsResult(not PERMISSION_GRANTED)")
                bluetoothLeScanner = bluetoothAdapter?.bluetoothLeScanner
                initRecyclerDevice()
            }
        }
    }

    private fun scanLeDevice() {
        bluetoothLeScanner?.let { scanner ->
            if (!scanning) { // Stops scanning after a pre-defined scan period.
                handler.postDelayed({
                    scanning = false
                    scanner.stopScan(leScanCallback)
                }, SCAN_PERIOD)
                scanning = true
                scanner.startScan(leScanCallback)
            } else {
                scanning = false
                scanner.stopScan(leScanCallback)
            }
        }
    }

    private val leScanCallback: ScanCallback = object : ScanCallback() {

        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)
            Log.d("test", "test du scanner")

            if (binding.searchViewSearchBar.query == result.device.name) {
                leDeviceListAdapter?.addDevice(result)
                //leDeviceListAdapter?.notifyDataSetChanged()
            }
            else {
                leDeviceListAdapter?.addDevice(result)
                leDeviceListAdapter?.notifyDataSetChanged()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ENABLE_BT && resultCode == RESULT_OK) {
            startBLEIfPossible()
        }
    }

    private fun isDeviceHasBLESupport(): Boolean {
        // Use this check to determine whether BLE is supported on the device. Then
        // you can selectively disable BLE-related features.
        if(!packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "Cet appareil n'est pas compatible, sorry", Toast.LENGTH_SHORT).show()
        }
        return packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)
    }

    private fun togglePlayPauseAction() {
        isScanning = !isScanning

        if (isScanning){
            binding.BleScanTitle.text = getString(R.string.ble_scan_pause_title)
            binding.ImageBLEStartPause.setImageResource(R.drawable.pause)
            binding.progressBarBLE.isVisible = true
            binding.JolieBarBLE.isVisible = false
            scanLeDevice()
        }
        else {
            binding.BleScanTitle.text = getString(R.string.ble_scan_play_title)
            binding.ImageBLEStartPause.setImageResource(R.drawable.play)
            binding.progressBarBLE.isVisible = false
            binding.JolieBarBLE.isVisible = true
        }
    }

    private fun initRecyclerDevice() {
        binding.RecyclerBleScan.layoutManager = LinearLayoutManager(this)

        leDeviceListAdapter = BLEScanAdapter(mutableListOf()) {
            val intent = Intent(this, DetailBleActivity::class.java)
            intent.putExtra(/*BluetoothDevice.EXTRA_DEVICE*/"ble_device", it.device)
            startActivity(intent)
        }
        binding.RecyclerBleScan.adapter = leDeviceListAdapter
    }



    companion object {
        const val REQUEST_ENABLE_BT = 22
        const val REQUEST_PERMISSION_LOCATION = 22
        const val SCAN_PERIOD: Long = 10000
    }
}
