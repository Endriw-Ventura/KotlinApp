package com.example.kotlinapp.extensions

import android.widget.ImageView
import coil.load
import com.example.kotlinapp.R

fun ImageView.carregaImagem(url: String? = null){
    load(url){
        fallback(R.drawable.erro)
        error(R.drawable.erro)
        placeholder(R.drawable.placeholder)
    }
}