package com.globant.marvelmvvm.data.mapper

interface BaseMapper<E, D> {
    fun transform(type: E): D
}