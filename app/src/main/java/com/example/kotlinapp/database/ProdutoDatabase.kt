package com.example.kotlinapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kotlinapp.converters.Conversor
import com.example.kotlinapp.database.dao.ProdutoDao
import com.example.kotlinapp.model.Produto

@Database(entities = [Produto::class], version = 1)
@TypeConverters(Conversor::class)
abstract class ProdutoDatabase : RoomDatabase(){
    abstract fun produtoDao(): ProdutoDao
}