package com.example.myquintaapp

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class VendedoresActivity : AppCompatActivity() {

    private lateinit var imgVendedorDestacado: ImageView
    private lateinit var photoUri: Uri  // Ahora es lateinit para evitar Smart Cast error

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

        // Evento para capturar foto
        btnCapturarFoto.setOnClickListener {
            abrirCamara()
        }

        // Botón para abrir el gráfico
        val btnVerGrafico = findViewById<Button>(R.id.btnVerGrafico)
        btnVerGrafico.setOnClickListener {
            val intent = Intent(this, GraficoVentasActivity::class.java)
            startActivity(intent)
        }
    }

    // 🔹 Método para abrir la cámara usando FileProvider
    private fun abrirCamara() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        // Crear un archivo para la imagen
        val photoFile = File(externalCacheDir, "photo_${System.currentTimeMillis()}.jpg")

        // Obtener la URI del archivo usando FileProvider
        photoUri = FileProvider.getUriForFile(this, "$packageName.provider", photoFile)

        // Agregar la URI como salida de la cámara
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)

        // Iniciar la cámara con Activity Result API
        cameraLauncher.launch(intent)
    }

    // 🔹 Registrar el resultado de la cámara
    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            mostrarImagen()
        }
    }

    // 🔹 Método para mostrar la imagen capturada
    private fun mostrarImagen() {
        val imageStream = contentResolver.openInputStream(photoUri)
        val bitmap = BitmapFactory.decodeStream(imageStream)
        imgVendedorDestacado.setImageBitmap(bitmap)
    }
}
