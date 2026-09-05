package com.example.trailsplit

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class Receiptdetails : AppCompatActivity() {
    lateinit var  receipttext : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_receiptdetails)
        receipttext = findViewById(R.id.receiptread)

        // Get image from receiptocrscreen
        val bitmap = intent.getParcelableExtra<Bitmap>("receipt_image")

        if(bitmap != null)
        {
            readReceipt(bitmap)

        }
        else{
            receipttext.text = "No image found for receipt"

        }

    }

    private fun readReceipt(bitmap: Bitmap) {
        // convert bitmap into ml kit inputimage
       val image  = InputImage.fromBitmap(bitmap,0)

         // create ML Kit text recogniser
        val recogniser = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)



        // ocr start
        recogniser.process(image).addOnSuccessListener {  visionText->

            //Get all text detected from receipt
            val detectedText = visionText.text

            if(detectedText.isNotEmpty()){
                receipttext.text = detectedText
            }
            else{
                receipttext.text = "No text found in receipt"
            }}
            .addOnFailureListener { exception ->
                receipttext.text  = "OCR failed : $(exception.message"
            }
    }
}