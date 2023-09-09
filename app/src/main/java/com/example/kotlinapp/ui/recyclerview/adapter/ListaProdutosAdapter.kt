package com.example.kotlinapp.ui.recyclerview.adapter

import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.PopupMenu
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapp.R
import com.example.kotlinapp.database.ProdutoDatabase
import com.example.kotlinapp.databinding.ProdutoItemBinding
import com.example.kotlinapp.extensions.carregaImagem
import com.example.kotlinapp.extensions.formataParaMoedaBrasileira
import com.example.kotlinapp.model.Produto
import com.example.kotlinapp.ui.activity.FormularioProdutoActivity
import com.example.kotlinapp.ui.activity.ProdutoDetalhesActivity



class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto> = emptyList()
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    class ViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun vincula(produto: Produto) {
            val nome = binding.produtoItemNome
            nome.text = produto.nome
            val descricao = binding.produtoItemDescricao
            descricao.text = produto.descricao
            val valor = binding.produtoItemPreco
            valor.text = produto.valor.formataParaMoedaBrasileira()
            if (produto.image != null) {
                View.VISIBLE
            } else {
                View.GONE
            }
            binding.produtoItemImageView.carregaImagem(produto.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)

        holder.itemView.setOnClickListener {
            val intent = ProdutoDetalhesActivity.newIntent(this.context, produto.uid)
            this.context.startActivity(intent)
        }

        holder.itemView.setOnLongClickListener {
            PopupMenu(context, holder.itemView).apply {
                inflate(R.menu.menu_editar_produto)
                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.id_menu_editar_produto -> {
                            val intent = FormularioProdutoActivity.newIntent(context, produto.uid)
                            context.startActivity(intent)
                            true
                        }
                        R.id.id_menu_excluir_produto -> {
                            deletaProduto(produto)
                            true
                        }
                        else -> false
                    }
                }
                show()
            }
            true
        }
    }

    override fun getItemCount(): Int = produtos.size

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

    fun deletaProduto(produto: Produto){
        val produtoDao = ProdutoDatabase.getDatabaseInstance(context).produtoDao()
        produtoDao.deleta(produto)
        atualiza(produtoDao.buscaTodos())
    }
}



