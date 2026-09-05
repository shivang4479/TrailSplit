package com.example.trailsplit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DeviceAdapter (
    private val deviceList: List<Device>,
    private val onItemClick:(Device)-> Unit //onItemclick is a function when ever user tap on device it run
):
        RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>() {
    class DeviceViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val txtname = itemView.findViewById<TextView>(R.id.txtname)
        val txtstatus = itemView.findViewById<TextView>(R.id.txtstatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_device, parent, false)
        return DeviceViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = deviceList[position]
        holder.txtname.text = device.name
        holder.txtstatus.text = device.status
        holder.itemView.setOnClickListener {
            onItemClick(device)
        }
    }

    override fun getItemCount(): Int {
        return deviceList.size
    }
}