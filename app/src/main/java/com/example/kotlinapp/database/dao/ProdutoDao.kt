package com.example.kotlinapp.database.dao

import androidx.room.*
import com.example.kotlinapp.model.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun buscaTodos() : List<Produto>

    @Query("SELECT * FROM Produto WHERE uid = :uid")
    fun buscaPeloUid(uid: Long) : Produto?

    @Insert
    fun adiciona(produto: Produto)

    @Delete
    fun deleta(produto: Produto)

    @Update
    fun update(produto: Produto)
}