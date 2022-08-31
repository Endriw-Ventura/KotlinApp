package com.example.kotlinapp.model

import java.math.BigDecimal

data class Produto (
    val nome: String,
    val descricao: String,
    val valor: BigDecimal,
    val image: String? = null
)