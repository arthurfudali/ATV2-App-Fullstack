package com.fatec.at2_base.models

import kotlinx.serialization.Serializable

@Serializable
data class Product (
    val id: Int,
    val name: String,
    val price: Double,
    val description: String? = null
)