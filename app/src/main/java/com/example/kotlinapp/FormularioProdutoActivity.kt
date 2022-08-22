package com.example.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity(R.layout.activity_formulario_produto) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val btnSalvar = findViewById<Button>(R.id.btn_salvar)
        btnSalvar.setOnClickListener{
            val campoNome = findViewById<EditText>(R.id.et_nome_item)
            val campoDescricao = findViewById<EditText>(R.id.et_descricao_item)
            val campoValor = findViewById<EditText>(R.id.et_valor_item)
            val nome = campoNome.text.toString()
            val descricao = campoDescricao.text.toString()
            val valor = campoValor.text.toString()
            val valorFormatado =  if(valor.isBlank()) BigDecimal.ZERO else BigDecimal(valor)
            val produtoNovo = Produto(nome, descricao, valorFormatado)
        }



    }
}