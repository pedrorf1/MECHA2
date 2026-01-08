package com.example.mecha

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//
class NotasAdapter(
    private val listaNotas: MutableList<Nota>
) : RecyclerView.Adapter<NotasAdapter.NotaViewHolder>() {

    class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.titulo)
        val descripcion: TextView = itemView.findViewById(R.id.descripcion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_nota, parent, false)
        return NotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = listaNotas[position]
        holder.titulo.text = nota.titulo
        holder.descripcion.text = nota.descripcion
    }

    override fun getItemCount() = listaNotas.size

    fun actualizarLista(nuevaLista: List<Nota>) {
        listaNotas.clear()
        listaNotas.addAll(nuevaLista)
        notifyDataSetChanged()
    }
}
