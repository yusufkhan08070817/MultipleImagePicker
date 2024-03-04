package com.example.multiple_image_picker

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MultipleImagePicker(private val activity: Activity) {
    companion object {
        const val PICK_IMAGE_MULTIPLE = 1
        const val STORAGE_PERMISSION_CODE = 101

    }


    lateinit var imagePath: String
    fun ImageReadAndWritePermision() {
        if (ContextCompat.checkSelfPermission(
                activity,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED||ContextCompat.checkSelfPermission(
                activity,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                STORAGE_PERMISSION_CODE
            )
        }
    }

    fun onpermisionresult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,successfull:()->Unit,Fail:()->Unit
    ){
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                successfull()

                // Permission granted, you can perform your operations that require this permission
            } else {
                // Permission denied
                // You can show a dialog or a toast here explaining why you need the permission and how to enable it.
                Fail()
            }
        }
    }

    fun imagepick() {
        if (Build.VERSION.SDK_INT < 19) {
            val intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            activity.startActivityForResult(
                Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE
            )
        } else {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            activity.startActivityForResult(intent, PICK_IMAGE_MULTIPLE)
        }
    }

    fun result(requestCode: Int, resultCode: Int, data: Intent?): ArrayList<String> {
        val ArrayList = ArrayList<String>()
        if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == Activity.RESULT_OK && data != null) {
            if (data.clipData != null) {
                val count = data.clipData!!.itemCount
                for (i in 0 until count) {
                    val imageUri: Uri = data.clipData!!.getItemAt(i).uri
                    ArrayList.add(getPathFromURI(imageUri))
                }
            } else if (data.data != null) {
                val imagePath: String? = data.data!!.path
                Log.e("imagePath", imagePath.toString())
            }
        }
        return ArrayList
    }

    private fun getPathFromURI(uri: Uri): String {
        val path: String = uri.path.toString()
        val currentList = ArrayList<String>()


        val databaseUri: Uri
        val selection: String?
        val selectionArgs: Array<String>?
        if (path.contains("/document/image:")) {
            databaseUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            selection = "_id=?"
            selectionArgs = arrayOf(DocumentsContract.getDocumentId(uri).split(":")[1])
        } else {
            databaseUri = uri
            selection = null
            selectionArgs = null
        }
        try {
            val projection = arrayOf(
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.ORIENTATION,
                MediaStore.Images.Media.DATE_TAKEN
            )
            val cursor = activity.contentResolver.query(
                databaseUri,
                projection, selection, selectionArgs, null
            )
            if (cursor != null && cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndex(projection[0])
                imagePath = cursor.getString(columnIndex)
                //currentList.add(imagePath)
            }
            cursor?.close()
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, e.message, e)
        }
        return imagePath
    }
}
