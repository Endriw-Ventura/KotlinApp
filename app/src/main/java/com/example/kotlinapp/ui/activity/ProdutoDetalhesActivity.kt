package com.example.kotlinapp.ui.activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinapp.databinding.ProdutoDetalhesBinding
import com.example.kotlinapp.extensions.carregaImagem
import com.example.kotlinapp.extensions.formataParaMoedaBrasileira
import com.example.kotlinapp.model.Produto


class ProdutoDetalhesActivity : AppCompatActivity() {

    companion object {
        private const val PRODUCT_KEY = "PRODUCT_KEY"
        fun newIntent(contexto: Context, produto: Produto) : Intent {
            return Intent(contexto, ProdutoDetalhesActivity::class.java)
                .apply {
                    putExtra(PRODUCT_KEY, produto)
                }
        }
    }

    private val binding by lazy {
        ProdutoDetalhesBinding.inflate(layoutInflater)
    }

    private val produto: Produto? by lazy{
        intent.getSerializableExtra(PRODUCT_KEY) as? Produto
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        produto?: finish()
        with(binding){
            produtoDetalhesActivityImagem.carregaImagem(produto?.image)
            produtoDetalhesActivityValor.text = produto?.valor?.formataParaMoedaBrasileira()
            produtoDetalhesActivityTitulo.text = produto?.nome
            produtoDetalhesActivityDescricao.text = produto?.descricao
        }
    }
}