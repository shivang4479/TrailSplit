package com.example.trailsplit

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.tileprovider.tilesource.TileSourceFactory

class Trip : AppCompatActivity() {
    lateinit var map: MapView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // osmdroid configuration
        Configuration.getInstance()
            .load(this, getSharedPreferences("osmdroid", MODE_PRIVATE))
        Configuration.getInstance().userAgentValue =
            "TrailSplit/1.0 (Android; com.example.trailsplit)"

        setContentView(R.layout.activity_trip)

        map = findViewById(R.id.map)
        map.setTileSource(TileSourceFactory.MAPNIK)
        //for multitouch zoom or enabling zoom button
        map.setMultiTouchControls(true)

        //Location starting point
        var location = GeoPoint(27.4924, 77.6737)

        // location zoom
        map.controller.setZoom(15)
        map.controller.setCenter(location)

        // Marker for location
        val marker = Marker(map)
        marker.position = location
        marker.title = " Your Location"
        map.overlays.add(marker)
    }
    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    }
