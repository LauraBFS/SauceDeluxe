package fr.isen.lau.saucedeluxe.categorie

import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.le.ScanResult
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.lau.saucedeluxe.R
import fr.isen.lau.saucedeluxe.databinding.CellBleDeviceBinding

class BLEScanAdapter(private val listBLE: MutableList<ScanResult>,
                     private val clickListener: (ScanResult) -> Unit) : RecyclerView.Adapter<BLEScanAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CellBleDeviceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.adressBLE.text = listBLE[position].device.toString()
        holder.numberID.text = listBLE[position].rssi.toString()
        holder.nameDevice.text = listBLE[position].device.name?: "No device name"

        holder.layoutBLE.setOnClickListener {
            clickListener.invoke(listBLE[position])
        }
    }

    override fun getItemCount(): Int = listBLE.size

    class ViewHolder(binding: CellBleDeviceBinding) : RecyclerView.ViewHolder(binding.root) {
        val adressBLE: TextView = itemView.findViewById(R.id.adresseDevice)
        val nameDevice: TextView = itemView.findViewById(R.id.NameDevice)
        val numberID: TextView = itemView.findViewById(R.id.buttonNumberId)
        val layoutBLE = itemView.findViewById<View>(R.id.CellListeBLE)
    }

    fun addDevice(AppareilData: ScanResult) {
        var deviceFound : Boolean = false

        listBLE.forEachIndexed { idx, sr ->
            if(sr.device.address == AppareilData.device.address){
                listBLE[idx] = AppareilData
                deviceFound = true
            }
        }

        if (!deviceFound){
            listBLE.add(AppareilData)
        }
/*
        val index = listBLE.indexOfFirst {
            it.device.address == AppareilData.device.address
        }
        if (index != -1) {
            listBLE[index] = AppareilData
        } else {
            listBLE.add(AppareilData)
        }*/
    }
}