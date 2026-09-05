package com.example.trailsplit

import android.os.Bundle
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReceiptOcrScreen : AppCompatActivity() {

    private lateinit var receiptimage: ImageView
    private var receiptBitmap : Bitmap? =null

    companion object {
        const val CAMERA_REQUEST = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt_ocr_screen)
        receiptimage = findViewById(R.id.scanimage)

        val btnretake = findViewById<Button>(R.id.retake)
        val btnscan = findViewById<Button>(R.id.scan)
        btnretake.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERA_REQUEST)
        }
        btnscan.setOnClickListener {
           if (receiptBitmap != null){
               val intent = Intent(this, Receiptdetails :: class.java)
               intent.putExtra("receipt_image",receiptBitmap)
               startActivity(intent)
           }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            val photo = data?.extras?.get("data") as Bitmap
            receiptBitmap  = photo
            receiptimage.setImageBitmap(photo)
        }
    }

}

