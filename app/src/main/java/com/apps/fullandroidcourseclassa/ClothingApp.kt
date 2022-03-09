package com.apps.fullandroidcourseclassa

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.fullandroidcourseclassa.adapters.ClothesImageAdapter
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_clothing_app.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

private const val REQUEST_CODE_IMAGE_PICK = 0

@Suppress("DEPRECATION")
class ClothingApp : AppCompatActivity() {
    var currentImage: Uri? = null
    val imageReference = Firebase.storage.reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clothing_app)

        //OPEN GALLERY
        ivClothingImage.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also {
                it.type = "image/*"
                startActivityForResult(it, REQUEST_CODE_IMAGE_PICK)
            }
        }
        //UPLOAD IMAGE
        btnUploadImage.setOnClickListener {
            uploadImageToStorage("myImage")
        }
        //DOWNLOAD IMAGE
        btnDownloadImage.setOnClickListener {
            downloadImageFromStorage("myImage")
        }
        //DELETE IMAGE
        btnDeleteImage.setOnClickListener {
            deleteImageFromStorage("myImage")
        }
        listAllImagesFromStorage()
    }

    //SET IMAGE TO VIEW
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_IMAGE_PICK) {
            data?.data.let {
                currentImage = it
                ivClothingImage.setImageURI(currentImage)
            }
        }
    }

    //SAVE IMAGE TO FIREBASE STORAGE
    private fun uploadImageToStorage(fileName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                currentImage?.let {
                    imageReference.child("images/$fileName").putFile(it).await()
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@ClothingApp,
                            "Image Uploaded Successfully",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ClothingApp, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    //DOWNLOAD IMAGE FROM FIREBASE STORAGE
    private fun downloadImageFromStorage(fileName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val maxDownloadSize = 5L * 1024 * 1024
                val imageByte =
                    imageReference.child("images/$fileName").getBytes(maxDownloadSize).await()
                val imageBitMap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.size)
                withContext(Dispatchers.Main) {
                    ivClothingImage.setImageBitmap(imageBitMap)
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ClothingApp, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    //DELETE IMAGE FROM FIREBASE STORAGE
    private fun deleteImageFromStorage(fileName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                imageReference.child("images/$fileName").delete().await()
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@ClothingApp,
                        "Image Deleted Successfully",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ClothingApp, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    //LIST ALL IMAGE FROM FIREBASE STORAGE
    private fun listAllImagesFromStorage() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val allImages = imageReference.child("images/").listAll().await()
                val listOfImagesUrls = mutableListOf<String>()
                for (singleImage in allImages.items){
                    val itemUrl = singleImage.downloadUrl.await()
                    listOfImagesUrls.add(itemUrl.toString())
                }
                withContext(Dispatchers.Main) {
                    val clothesImageAdapter = ClothesImageAdapter(listOfImagesUrls)
                    rvClothingImages.apply {
                        adapter = clothesImageAdapter
                        layoutManager = LinearLayoutManager(this@ClothingApp)
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ClothingApp, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}