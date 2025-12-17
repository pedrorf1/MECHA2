package com.example.mecha

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge

import android.widget.Toast
import kotlin.jvm.java
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.mecha.models.Mecanico
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Button
import android.content.Intent
import androidx.privacysandbox.tools.core.model.Method
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Request
class CatalogoMecanicoActivity : AppCompatActivity() {

    private lateinit var rv: RecyclerView
    private lateinit var adapter: MecanicoAdapter
    private val listaMecanicos = ArrayList<Mecanico>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.catalogo_mecanico)

        rv = findViewById(R.id.rvMecanicos)
        rv.layoutManager = LinearLayoutManager(this)

        adapter = MecanicoAdapter(listaMecanicos) { mecanico ->
            Toast.makeText(
                this,
                "Elegiste: ${mecanico.IdMecanico}",
                Toast.LENGTH_SHORT
            ).show()
        }

        rv.adapter = adapter

        cargarMecanicos()

        findViewById<Button>(R.id.btnGuardarId).setOnClickListener {
            val id = findViewById<EditText>(R.id.edtIdMecanico).text.toString()
            guardarId(id)
        }

    }

    private fun cargarMecanicos() {
        val url = "http://10.0.2.2/mecha/obtener_mecanico.php"

        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->

                listaMecanicos.clear()

                for (i in 0 until response.length()) {
                    val obj = response.getJSONObject(i)

                    val mecanico = Mecanico(
                        obj.optInt("IdMecanico", 0),
                        obj.optString("Ubicacion", "Sin ubicación"),
                        obj.optString("Especialidad", "No definida"),
                        obj.optString("Avale", "Sin avales"),
                        obj.optDouble("Ranking", 0.0),
                        obj.optInt("IdPersona", 0),
                        obj.optString("telefono", "0")
                    )

                    listaMecanicos.add(mecanico)
                }

                adapter.notifyDataSetChanged()
            },
            { error ->
                Toast.makeText(
                    this,
                    "Error: ${error.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        )

        Volley.newRequestQueue(this).add(request)
    }

    private fun guardarId(id: String) {
        val prefs = getSharedPreferences("datos", MODE_PRIVATE)
        prefs.edit().putString("id_mecanico", id).apply()
        Toast.makeText(this, "ID guardado correctamente", Toast.LENGTH_SHORT).show()
    }
}

