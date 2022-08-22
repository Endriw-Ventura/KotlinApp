package com.example.kotlinapp
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Tia Taloma Boboca", Toast.LENGTH_SHORT).show()
        val recyclerView = findViewById<RecyclerView>(R.id.rv_lista_items)
        recyclerView.adapter = ListaProdutosAdapter(this, listOf())
        val fabAddItem = findViewById<FloatingActionButton>(R.id.btn_fab_add_item)
        fabAddItem.setOnClickListener{
            val intent = Intent(this, FormularioProdutoActivity::class.java)
            startActivity(intent)
        }
    }
}