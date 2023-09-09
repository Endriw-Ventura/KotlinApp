package com.example.kotlinapp.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinapp.consts.PRODUCT_KEY
import com.example.kotlinapp.database.ProdutoDatabase
import com.example.kotlinapp.databinding.ActivityFormularioProdutoBinding
import com.example.kotlinapp.extensions.carregaImagem
import com.example.kotlinapp.model.Produto
import com.example.kotlinapp.ui.dialog.FormularioImageDialog
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    companion object {
        fun newIntent(contexto: Context, uid: Long) : Intent {
            return Intent(contexto, FormularioProdutoActivity::class.java)
                .apply {
                    putExtra(PRODUCT_KEY, uid)
                }
        }
    }

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var url: String? = null
    private var idProduto: Long = 0L
    private var produto: Produto? = null
    val dao by lazy {
        ProdutoDatabase.getDatabaseInstance(this).produtoDao()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
        binding.activityFormularioProdutoImageView.setOnClickListener{
            FormularioImageDialog(this).mostra{ imagem ->
                url = imagem
                binding.activityFormularioProdutoImageView.carregaImagem(url)
            }
        }
        idProduto = intent.getLongExtra(PRODUCT_KEY, 0)
        produto = dao.buscaPeloUid(idProduto)
        produto?.let{ produtoCarregado ->
            idProduto = produtoCarregado.uid
            binding.activityFormularioProdutoImageView
                .carregaImagem(produtoCarregado.image)
            binding.activityFormularioProdutoNome
                .setText(produtoCarregado.nome)
            binding.activityFormularioProdutoDescricao
                .setText(produtoCarregado.descricao)
            binding.activityFormularioProdutoPreco
                .setText(produtoCarregado.valor.toPlainString())
        }
    }


    private fun configuraBotaoSalvar() {
        val btnSalvar = binding.activityFormularioProdutoBtnSalvar

        if (produto != null){
            btnSalvar.setText("Atualizar")
        }

        btnSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            if (produtoNovo.uid > 0){
                dao.update(produtoNovo)
            }else{
                dao.adiciona(produtoNovo)
            }
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val campoNome = binding.activityFormularioProdutoNome
        val campoDescricao = binding.activityFormularioProdutoDescricao
        val campoValor = binding.activityFormularioProdutoPreco
        val nome = campoNome.text.toString()
        val descricao = campoDescricao.text.toString()
        val valor = campoValor.text.toString()
        val valorFormatado = if (valor.isBlank()) BigDecimal.ZERO else BigDecimal(valor)
        return Produto(uid = idProduto, nome = nome, descricao = descricao, valor = valorFormatado, image = url)
    }
}