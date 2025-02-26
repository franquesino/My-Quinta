package com.example.myquintaapp

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VendedorAdapter(
    private val context: Context,
    private val vendedores: List<Vendedor>
) : RecyclerView.Adapter<VendedorAdapter.VendedorViewHolder>() {

    private var mediaPlayer: MediaPlayer? = null

    class VendedorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgVendedor: ImageView = view.findViewById(R.id.imgVendedor)
        val tvNombreVendedor: TextView = view.findViewById(R.id.tvNombreVendedor)
        val tvAreaVendedor: TextView = view.findViewById(R.id.tvAreaVendedor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VendedorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vendedor, parent, false)
        return VendedorViewHolder(view)
    }

    override fun onBindViewHolder(holder: VendedorViewHolder, position: Int) {
        val vendedor = vendedores[position]
        holder.imgVendedor.setImageResource(vendedor.fotoResId)
        holder.tvNombreVendedor.text = vendedor.nombre
        holder.tvAreaVendedor.text = vendedor.area

        // Agregar evento de clic
        holder.itemView.setOnClickListener {
            if (position == 2) { // Si es el tercer vendedor (índice 2)
                reproducirAudio()
            }
        }
    }

    override fun getItemCount() = vendedores.size

    private fun reproducirAudio() {
        mediaPlayer?.release() // Liberar cualquier instancia anterior
        mediaPlayer = MediaPlayer.create(context, R.raw.audio_vendedor)
        mediaPlayer?.start()
    }
}
