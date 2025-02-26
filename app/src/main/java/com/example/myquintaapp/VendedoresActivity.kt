package com.example.myquintaapp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class VendedoresActivity : AppCompatActivity() {

    private lateinit var imgVendedorDestacado: ImageView
    private lateinit var photoUri: Uri
    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendedores)

        val vendedores = listOf(
            Vendedor("Carlos Pérez", "Electrónica", R.drawable.vendedor1),
            Vendedor("María López", "Moda", R.drawable.vendedor2),
            Vendedor("Juan Rodríguez", "Hogar", R.drawable.vendedor3),
            Vendedor("Ana Fernández", "Deportes", R.drawable.vendedor4),
            Vendedor("Luis Gómez", "Alimentos", R.drawable.vendedor5)
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rvVendedores)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = VendedorAdapter(this, vendedores)

        imgVendedorDestacado = findViewById(R.id.imgVendedorDestacado)
        val btnCapturarFoto = findViewById<Button>(R.id.btnCapturarFoto)

        btnCapturarFoto.setOnClickListener {
            abrirCamara()
        }

        val btnVerGrafico = findViewById<Button>(R.id.btnVerGrafico)
        btnVerGrafico.setOnClickListener {
            val intent = Intent(this, GraficoVentasActivity::class.java)
            startActivity(intent)
        }
    }

    private fun abrirCamara() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val photoFile = crearArchivoImagen()
        if (photoFile != null) {
            photoUri = FileProvider.getUriForFile(
                this,
                "${applicationContext.packageName}.provider",
                photoFile
            )
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            imgVendedorDestacado.setImageURI(photoUri)
        }
    }

    private fun crearArchivoImagen(): File? {
        return try {
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val storageDir = getExternalFilesDir(null)
            File.createTempFile("IMG_$timeStamp", ".jpg", storageDir)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
