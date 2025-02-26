package com.example.myquintaapp
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myquintaapp.GraficoVentasActivity




class VendedoresActivity : AppCompatActivity() {
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

        // Botón para abrir el gráfico
        val btnVerGrafico = findViewById<Button>(R.id.btnVerGrafico)
        btnVerGrafico.setOnClickListener {
            val intent = Intent(this, GraficoVentasActivity::class.java)
            startActivity(intent)
        }
    }
}
