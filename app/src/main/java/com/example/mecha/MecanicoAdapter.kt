package com.example.mecha

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mecha.models.Mecanico
import android.widget.RatingBar
import android.widget.Button
import android.net.Uri
import android.content.Intent

class MecanicoAdapter(
    private val lista: List<Mecanico>,
    private val onItemClick: (Mecanico) -> Unit
) : RecyclerView.Adapter<MecanicoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre = itemView.findViewById<TextView>(R.id.txtNombre)
        val ubicacion = itemView.findViewById<TextView>(R.id.txtubi)
        val especialidad = itemView.findViewById<TextView>(R.id.txtEspecialidad)
        val ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)

        val btnWhatsapp: Button = itemView.findViewById(R.id.btnWhatsapp)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mecanico, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val m = lista[position]

        holder.nombre.text = "Mecánico #${m.IdMecanico}"
        holder.ubicacion.text="Ubicacion: ${m.Ubicacion}"
        holder.especialidad.text = "Especialidad: ${m.Especialidad}"
        holder.ratingBar.rating = m.Ranking.toFloat()
        holder.btnWhatsapp.setOnClickListener {

            val telefono = "52${m.Telefono}" //
            val mensaje = "Hola, vi tu perfil en la app y necesito un mecánico"

            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://wa.me/$telefono?text=${Uri.encode(mensaje)}")
            )

            it.context.startActivity(intent)
        }


        holder.itemView.setOnClickListener {
            onItemClick(m)
        }
    }

    override fun getItemCount() = lista.size
}
