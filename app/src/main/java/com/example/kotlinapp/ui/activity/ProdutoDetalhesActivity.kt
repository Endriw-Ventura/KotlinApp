package com.example.kotlinapp.ui.activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinapp.consts.PRODUCT_KEY
import com.example.kotlinapp.database.ProdutoDatabase
import com.example.kotlinapp.databinding.ProdutoDetalhesBinding
import com.example.kotlinapp.extensions.carregaImagem
import com.example.kotlinapp.extensions.formataParaMoedaBrasileira
import com.example.kotlinapp.model.Produto


class ProdutoDetalhesActivity : AppCompatActivity() {

    companion object {
        fun newIntent(contexto: Context, uid: Long) : Intent {
            return Intent(contexto, ProdutoDetalhesActivity::class.java)
                .apply {
                    putExtra(PRODUCT_KEY, uid)
                }
        }
    }
    private val binding by lazy {
        ProdutoDetalhesBinding.inflate(layoutInflater)
    }
    private var idProduto : Long = 0L
    private var produto: Produto? = null
    private val produtoDAO by lazy {
        ProdutoDatabase.getDatabaseInstance(this).produtoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        idProduto = intent.getLongExtra(PRODUCT_KEY, 0)
    }

    override fun onResume() {
        super.onResume()
        buscaProduto()
    }

    private fun buscaProduto() {
        produto = produtoDAO.buscaPeloUid(idProduto)
        produto?.let{
            preencheCampos(it)
        } ?: finish()
    }

    private fun preencheCampos(it: Produto) {
        with(binding){
            produtoDetalhesActivityImagem.carregaImagem(it.image)
            produtoDetalhesActivityValor.text = it.valor.toPlainString()
            produtoDetalhesActivityTitulo.text = it.nome
            produtoDetalhesActivityDescricao.text = it.descricao
        }
    }
}
