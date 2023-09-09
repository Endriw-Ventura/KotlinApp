package com.example.kotlinapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.math.BigDecimal

@Entity
data class Produto (
    @PrimaryKey(autoGenerate = true) val uid: Long = 0L,
    val nome: String,
    val descricao: String,
    val valor: BigDecimal,
    val image: String? = null
) : Serializable