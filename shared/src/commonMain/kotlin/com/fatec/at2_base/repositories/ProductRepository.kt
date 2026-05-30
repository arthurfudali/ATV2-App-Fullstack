package com.fatec.at2_base.repositories

import com.fatec.at2_base.models.Product

interface ProductRepository {
    suspend fun getAllProducts(): List<Product>
    suspend fun getProductById(id: Int): Product?
    suspend fun addProduct(product: Product)
    suspend fun updateProduct(id: Int, product: Product): Product
    suspend fun deleteProduct(id: Int)
}