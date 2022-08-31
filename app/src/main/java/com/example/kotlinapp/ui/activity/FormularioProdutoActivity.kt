package com.example.kotlinapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.kotlinapp.R
import com.example.kotlinapp.dao.ProdutosDAO
import com.example.kotlinapp.databinding.ActivityFormularioProdutoBinding
import com.example.kotlinapp.databinding.FormularioImagemBinding
import com.example.kotlinapp.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
        binding.activityFormularioProdutoImageView.setOnClickListener{
            val bindingFormularioimage = FormularioImagemBinding.inflate(layoutInflater)
            bindingFormularioimage.formularioImagemBtnAddImage.setOnClickListener{
                url = bindingFormularioimage.formularioImagemUrl.text.toString()
                bindingFormularioimage.formularioImagemImageView.load(url)
            }


            AlertDialog.Builder(this)
                .setView(bindingFormularioimage.root)
                .setPositiveButton("Confirmar") { _, _ ->
                    url = bindingFormularioimage.formularioImagemUrl.text.toString()
                    binding.activityFormularioProdutoImageView.load(url)

                }
                .setNegativeButton("Cancelar") { _, _ -> }
                .show()
        }
    }


    private fun configuraBotaoSalvar() {
        val btnSalvar = binding.activityFormularioProdutoBtnSalvar
        val dao = ProdutosDAO()
        btnSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            dao.adiciona(produtoNovo)
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
        return Produto(nome, descricao, valorFormatado, url)
    }
}