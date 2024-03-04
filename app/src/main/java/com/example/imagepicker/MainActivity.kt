package com.example.imagepicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagepicker.classes.adopter
import com.example.multiple_image_picker.MultipleImagePicker

class MainActivity : AppCompatActivity() {


    lateinit var recycler: RecyclerView
    lateinit var imagePicker: MultipleImagePicker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imagePicker = MultipleImagePicker(this)

        imagePicker.ImageReadAndWritePermision()
        // Request storage permission if not granted

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            recycler = findViewById(R.id.recycler)
            recycler.layoutManager = LinearLayoutManager(this)
            imagePicker.imagepick()
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e("image", "${imagePicker.result(requestCode, resultCode, data)}")
        recycler.adapter = adopter(imagePicker.result(requestCode, resultCode, data), this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        imagePicker.onpermisionresult(requestCode, permissions, grantResults, {
            Toast.makeText(this, "grante", Toast.LENGTH_SHORT).show()
        }, { Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show() })
    }
}