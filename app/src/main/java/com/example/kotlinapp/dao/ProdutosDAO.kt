
package com.example.kotlinapp.dao

import com.example.kotlinapp.model.Produto

class ProdutosDAO {

    fun adiciona(produto: Produto){
        produtos.add(produto)
    }

    fun buscaTodos(): List<Produto> {
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>()
    }
}