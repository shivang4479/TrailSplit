package com.example.trailsplit

import android.Manifest
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.nearby.Nearby
import com.google.android.gms.nearby.connection.ConnectionsClient
import com.google.android.gms.nearby.connection.AdvertisingOptions
import com.google.android.gms.nearby.connection.Strategy
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback
import com.google.android.gms.nearby.connection.ConnectionInfo
import com.google.android.gms.nearby.connection.ConnectionResolution
import com.google.android.gms.nearby.connection.DiscoveredEndpointInfo
import com.google.android.gms.nearby.connection.DiscoveryOptions
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback
import com.google.android.gms.nearby.connection.PayloadCallback
import com.google.android.gms.nearby.connection.Payload
import com.google.android.gms.nearby.connection.PayloadTransferUpdate

class Syncscreen : AppCompatActivity() {


    private lateinit var bluetoothIcon: ImageView//for bluetooth animation icon
    private lateinit var recyclerView: RecyclerView//nearby device recyclerview

    private lateinit var adapter: DeviceAdapter

    private lateinit var connectionsClient: ConnectionsClient //this object used for everything in nearby connections

    private val SERVICE_ID="com.example.trailsplit"
    private val DEVICE_NAME=android.os.Build.MODEL
    private val nearbyDevice = mutableListOf<Device>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_syncscreen)

        connectionsClient= Nearby.getConnectionsClient(this)
        startAdvertising()
        startDiscovery()


        recyclerView = findViewById(R.id.rvDevices)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = DeviceAdapter(nearbyDevice) { device ->
           connectionsClient.requestConnection(DEVICE_NAME,device.endpointId,connectionLifecycleCallback)
        }
        recyclerView.adapter = adapter

        bluetoothIcon = findViewById(R.id.bluetoothsync)
        val pulseAnim = AnimationUtils.loadAnimation(this, R.anim.pulse)
        bluetoothIcon.startAnimation(pulseAnim)
    }

    //call this function when syncing complete or stops
    private fun stopSyncing() {
        bluetoothIcon.clearAnimation()
    }

    private val connectionLifecycleCallback=object : ConnectionLifecycleCallback(){
        override fun onConnectionInitiated(endpointID: String, connectionInfo: ConnectionInfo) {
            connectionsClient.acceptConnection(endpointID,payloadCallback)
            Toast.makeText(this@Syncscreen,"Conecction request from ${connectionInfo.endpointName}",
                Toast.LENGTH_SHORT).show()
        }

        override fun onConnectionResult(p0: String, p1: ConnectionResolution) {
            Toast.makeText(this@Syncscreen,"Connected", Toast.LENGTH_SHORT).show()
        }

        override fun onDisconnected(p0: String) {
            Toast.makeText(this@Syncscreen,"Disconnected", Toast.LENGTH_SHORT).show()
        }
    }

    private val payloadCallback=object : PayloadCallback(){
        override fun onPayloadReceived(p0: String, p1: Payload) {
            TODO("Not yet implemented")
        }
        override fun onPayloadTransferUpdate(p0: String, p1: PayloadTransferUpdate) {
            TODO("Not yet implemented")
        }
    }



    private fun startAdvertising(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!=
            PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION),100)
            return
        }
        connectionsClient.startAdvertising(DEVICE_NAME,SERVICE_ID,connectionLifecycleCallback,
            AdvertisingOptions.Builder().setStrategy(Strategy.P2P_CLUSTER).build()).addOnSuccessListener {
            Toast.makeText(this,"Advertising Started", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {e->
            Toast.makeText(this,"Advertising Failed:${e.message}", Toast.LENGTH_SHORT).show()
        }
    }


    private fun startDiscovery(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!=
            PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION),101)
            return
        }
        val option= DiscoveryOptions.Builder().setStrategy(Strategy.P2P_CLUSTER).build()
        connectionsClient.startDiscovery(SERVICE_ID,endpointDiscoveryCallback,option)
            .addOnSuccessListener {
                Toast.makeText(this,"Discovery Started", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e->
                Toast.makeText(this,"Discovery Failed:${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private val endpointDiscoveryCallback=object : EndpointDiscoveryCallback(){
        override fun onEndpointFound(p0: String, info: DiscoveredEndpointInfo) {
            nearbyDevice.add(Device(info.endpointName,"Available",p0))
            adapter.notifyDataSetChanged()
            Toast.makeText(this@Syncscreen,"Found${info.endpointName}", Toast.LENGTH_SHORT).show()
        }

        override fun onEndpointLost(p0: String) {
            Toast.makeText(this@Syncscreen,"Device Lost", Toast.LENGTH_SHORT).show()
        }
    }

    // to stop scanning for device when sync screen closed
    override fun onDestroy() {
        super.onDestroy()

    }
}

