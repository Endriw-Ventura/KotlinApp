package com.example.kotlinapp.converters

import java.math.BigDecimal
import androidx.room.TypeConverter

class Conversor {

    @TypeConverter
    fun doubleParaBigDecimal(valor: Double?): BigDecimal {
        return valor?.let { BigDecimal(valor.toString()) } ?: BigDecimal.ZERO
    }

    @TypeConverter
    fun bigDecimalParaDouble(valor: BigDecimal?) : Double? {
        return valor?.let { valor.toDouble() }
    }
}