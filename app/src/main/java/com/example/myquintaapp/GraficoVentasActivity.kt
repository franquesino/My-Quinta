package com.example.myquintaapp

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class GraficoVentasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grafico_ventas)

        val barChart = findViewById<BarChart>(R.id.barChart)

        // Datos de ventas de los vendedores
        val vendedores = listOf("Carlos", "María", "Juan", "Ana", "Luis")
        val ventas = listOf(50, 75, 40, 90, 60)

        // Crear entradas para el gráfico
        val entries = ventas.mapIndexed { index, venta -> BarEntry(index.toFloat(), venta.toFloat()) }

        // Crear dataset
        val dataSet = BarDataSet(entries, "Ventas de Vendedores")
        dataSet.color = Color.BLUE
        dataSet.valueTextSize = 14f

        // Configurar gráfico
        val barData = BarData(dataSet)
        barChart.data = barData

        // Configurar eje X con nombres de vendedores
        val xAxis = barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(vendedores)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.setDrawGridLines(false)

        // Configurar eje Y
        barChart.axisLeft.axisMinimum = 0f
        barChart.axisRight.isEnabled = false

        // Otros ajustes
        barChart.description.isEnabled = false
        barChart.setFitBars(true)
        barChart.invalidate() // Refrescar gráfico
    }
}
