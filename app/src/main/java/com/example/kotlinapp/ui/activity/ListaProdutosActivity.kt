package com.example.kotlinapp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinapp.database.ProdutoDatabase
import com.example.kotlinapp.databinding.ListaProdutosActivityBinding
import com.example.kotlinapp.ui.recyclerview.adapter.ListaProdutosAdapter

class ListaProdutosActivity : AppCompatActivity() {

    private val adapter = ListaProdutosAdapter(context = this)
    private val binding by lazy {
        ListaProdutosActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()
    }

    override fun onResume() {
        val db = ProdutoDatabase.getDatabaseInstance(this)
        val produtoDAO = db.produtoDao()
        adapter.atualiza(produtoDAO.buscaTodos())
        super.onResume()
    }

    private fun configuraFab() {
        val fab = binding.listaProdutosActivityFabAddItem
        fab.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

    private fun vaiParaFormularioProduto() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.listaProdutosActivityListaDeItems
        recyclerView.adapter = adapter
    }
}