package com.mads2202.myphotogallery

import android.Manifest
import android.content.ContentUris
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mads2202.myphotogallery.model.Photo
import com.mads2202.myphotogallery.model.PhotoLab

class MainActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_READ_EXTERNAL_STORAGE_PERMISSION = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_READ_EXTERNAL_STORAGE_PERMISSION
            )
        }
        getPhotos()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container,PhotoListFragment.newInstance()).commit()
    }

    private fun getPhotos() {
        val collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE
        )
        val cursor = contentResolver.query(collection, projection, null, null, null)
        if (cursor != null) {
            var id = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            var name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            var size = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
            while (cursor.moveToNext()) {
                val photoId = cursor.getLong(id)
                val photoName = cursor.getString(name)
                val photoSize = cursor.getString(size)
                PhotoLab.photos.add(
                    Photo(
                        ContentUris.withAppendedId(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            photoId
                        ), photoName, photoSize
                    )
                )
            }
            cursor.close()
        }
    }
}