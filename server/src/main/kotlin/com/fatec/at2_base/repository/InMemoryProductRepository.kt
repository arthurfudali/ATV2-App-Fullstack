package com.fatec.at2_base.repository

import com.fatec.at2_base.models.Product
import com.fatec.at2_base.repositories.ProductRepository

class InMemoryProductRepository : ProductRepository {
    private val products = mutableListOf(
        Product(id = 1, name = "Smartphone", price = 999.99, description = "A high-end smartphone with a great camera"),
        Product(id = 2, name = "Laptop", price = 1499.99, description = "A powerful laptop for work and gaming"),
        Product(id = 3, name = "Headphones", price = 199.99, description = "Noise-cancelling over-ear headphones"),
        Product(id = 4, name = "Smartwatch", price = 299.99, description = "A stylish smartwatch with fitness tracking")
    )

    private var nextId: Int = products.maxOfOrNull { it.id }?.plus(1) ?: 1

    override suspend fun getAllProducts(): List<Product> = synchronized(products) {
        products.toList()
    }

    override suspend fun getProductById(id: Int): Product? = synchronized(products) {
        products.firstOrNull { it.id == id }
    }

    override suspend fun addProduct(product: Product) {
        synchronized(products) {
            val productToStore = if (product.id > 0 && products.none { it.id == product.id }) {
                product
            } else {
                product.copy(id = nextId++)
            }
            products.add(productToStore)
        }
    }

    override suspend fun updateProduct(id: Int, product: Product): Product = synchronized(products) {
        val index = products.indexOfFirst { it.id == id }
        val updatedProduct = product.copy(id = id)
        if (index >= 0) {
            products[index] = updatedProduct
            updatedProduct
        } else {
            throw NoSuchElementException("Product with id=$id not found")
        }
    }

    override suspend fun deleteProduct(id: Int) {
        synchronized(products) {
            products.removeIf { it.id == id }
        }
    }
}

