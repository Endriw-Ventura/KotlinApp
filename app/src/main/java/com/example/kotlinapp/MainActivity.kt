package com.example.kotlinapp
import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.math.BigDecimal

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Tia Taloma Boboca", Toast.LENGTH_SHORT).show()
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_lista_items)
        recyclerView.adapter = ListaProdutosAdapter(this, listOf(

            Produto("teste", "teste", BigDecimal("19.99")),
            Produto("teste", "teste", BigDecimal("19.99")),
            Produto("teste", "teste", BigDecimal("19.99")),
            Produto("teste", "teste", BigDecimal("19.99")),
            Produto("teste", "teste", BigDecimal("19.99")),
            Produto("teste", "teste", BigDecimal("19.99")),
            Produto("teste", "teste", BigDecimal("19.99")),
            Produto("teste", "teste", BigDecimal("19.99")),
            Produto("teste", "teste", BigDecimal("19.99")),
            Produto("teste", "teste", BigDecimal("19.99")),
            Produto("teste", "teste", BigDecimal("19.99")),
            Produto("teste", "teste", BigDecimal("19.99")),
            Produto("teste", "teste", BigDecimal("19.99")),
            Produto("teste", "teste", BigDecimal("19.99")),
            Produto("teste", "teste", BigDecimal("19.99"))

        ))
    }
}