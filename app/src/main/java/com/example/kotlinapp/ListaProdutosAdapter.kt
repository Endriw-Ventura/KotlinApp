package com.example.kotlinapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListaProdutosAdapter(
    private val context: Context,
    private val produtos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun Vincula(produto: Produto) {

            val campoNome = itemView.findViewById<TextView>(R.id.tv_nome_item)
            val campoDescricao = itemView.findViewById<TextView>(R.id.tv_descricao_item)
            val campoValor = itemView.findViewById<TextView>(R.id.tv_preco_item)

            campoNome.text = produto.nome
            campoDescricao.text = produto.descricao
            campoValor.text = produto.valor.toPlainString()

        }

    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.produto_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.Vincula(produtos[position])
    }

    override fun getItemCount(): Int = produtos.size
}

