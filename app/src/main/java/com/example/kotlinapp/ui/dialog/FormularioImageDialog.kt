package com.example.kotlinapp.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.kotlinapp.databinding.FormularioImagemBinding
import com.example.kotlinapp.extensions.carregaImagem

class FormularioImageDialog(private val context: Context) {
    fun mostra(
        urlPadrao: String? = null,
        quandoImagemCarregada: (imagem: String) -> Unit
    ) {
        FormularioImagemBinding.inflate(LayoutInflater.from(context)).apply {
            urlPadrao?.let {
                formularioImagemImageView.carregaImagem(urlPadrao)
                formularioImagemUrl.setText(urlPadrao)
            }

            formularioImagemBtnAddImage.setOnClickListener {
                val url = formularioImagemUrl.text.toString()
                formularioImagemImageView.carregaImagem(url)
            }

            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirmar") { _, _ ->
                    val url = formularioImagemUrl.text.toString()
                    quandoImagemCarregada(url)
                }
                .setNegativeButton("Cancelar") { _, _ -> }
                .show()
        }
    }
}