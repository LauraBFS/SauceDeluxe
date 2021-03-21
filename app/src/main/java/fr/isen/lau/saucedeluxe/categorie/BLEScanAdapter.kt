package fr.isen.lau.saucedeluxe.categorie

import android.bluetooth.le.ScanResult
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import fr.isen.lau.saucedeluxe.R
import fr.isen.lau.saucedeluxe.databinding.CellBleDeviceBinding

class BLEScanAdapter(private val listBLE: MutableList<ScanResult>/*,
                     private val clickListener: (ScanResult) -> Unit*/) : RecyclerView.Adapter<BLEScanAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CellBleDeviceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.adressBLE.text = listBLE[position].device.toString()
        holder.numberID.text = listBLE[position].scanRecord?.advertiseFlags.toString()
        holder.nameDevice.text = listBLE[position].scanRecord?.deviceName.toString()

        holder.layoutBLE.setOnClickListener {
            //clickListener.invoke(listBLE[position])
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
        if (!listBLE.contains(AppareilData)) {
            listBLE.add(AppareilData)
        }
    }
}