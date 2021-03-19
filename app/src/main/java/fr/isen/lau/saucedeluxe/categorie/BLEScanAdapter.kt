package fr.isen.lau.saucedeluxe.categorie

import android.bluetooth.BluetoothDevice
import android.bluetooth.le.ScanRecord
import android.bluetooth.le.ScanResult
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import fr.isen.lau.saucedeluxe.R
import fr.isen.lau.saucedeluxe.databinding.CellBleDeviceBinding
import fr.isen.lau.saucedeluxe.model.Item

class BLEScanAdapter(private val listBLE: MutableList<ScanResult>/*, private val clickListener: (ScanResult) -> Unit*/
                      ) : RecyclerView.Adapter<BLEScanAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CellBleDeviceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.AdressBLE.text = listBLE[position].device.toString()
        holder.numberID.text = listBLE[position].scanRecord?.advertiseFlags.toString()
        holder.nameDevice.text = listBLE[position].scanRecord?.deviceName.toString()

        holder.layoutBLE.setOnClickListener {
            //clickListener.invoke(listBLE[position])
        }
    }

    override fun getItemCount(): Int = listBLE.size

    class ViewHolder(binding: ConstraintLayout) : RecyclerView.ViewHolder(binding) {
        val AdressBLE: TextView = itemView.findViewById(R.id.adresseDevice)
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