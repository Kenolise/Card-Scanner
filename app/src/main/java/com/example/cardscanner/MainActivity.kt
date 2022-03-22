package com.example.cardscanner

import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.camerakit.CameraKitView
import com.example.cardscanner.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage


class MainActivity : AppCompatActivity() {
    private val cameraKitView: CameraKitView? = null
    private lateinit var binding: ActivityMainBinding

            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                binding = ActivityMainBinding.inflate(layoutInflater)
                val view = binding.root
                setContentView(view)
    }

    private fun getCardDetails(bitmap: Bitmap) {
        val image = FirebaseVisionImage.fromBitmap(bitmap)
        val firebaseVisionTextDetector = FirebaseVision.getInstance().cloudTextRecognizer

        firebaseVisionTextDetector.processImage(image)
            .addOnSuccessListener {
                val words = it.text.split("\n")
                for (word in words) {
                    Log.e("TAG", word)
                    //REGEX for detecting a credit card

                    val tvCardNumber = null
                    if (word.replace(" ", "").matches(Regex("^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})\$")))
                        tvCardNumber.text = word
                    //Find a better way to do this
                    if (word.contains("/")) {
                        for (year in word.split(" ")) {
                            val tvCardExpiry = null
                            if (year.contains("/"))
                                tvCardExpiry.text = year
                        }
                    }
                }
                val sheetBehavior = null
                sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                val fabProgressCircle = null
                fabProgressCircle.hide()
            }
            .addOnFailureListener {
                val fabProgressCircle = null
                fabProgressCircle.hide()
                Toast.makeText(baseContext, "Sorry, something went wrong!", Toast.LENGTH_SHORT).show()


            }

    }


    private fun openCamera(){
        binding.camButton.setOnClickListener{

        }
    }

private fun bounds(){
    val firebaseVisionCloudText = null
    for (page in firebaseVisionCloudText.getPages()) {
        val languages = page.getTextProperty().getDetectedLanguages()
        val height = page.getHeight()
        val width = page.getWidth()
        val confidence = page.getConfidence()

        for (block in page.getBlocks()) {
            val boundingBox : Rect = block.getBoundingBox()
            val blockLanguages = block.getTextProperty().getDetectedLanguages()
            val blockConfidence = block.getConfidence()
            // And so on: Paragraph, Word, Symbol
        }
    }
}
    
    
}