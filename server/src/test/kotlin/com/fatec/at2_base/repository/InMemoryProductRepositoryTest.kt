package com.fatec.at2_base.repository

import com.fatec.at2_base.models.Product
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlinx.coroutines.runBlocking

class InMemoryProductRepositoryTest {
    private val repository = InMemoryProductRepository()

    @Test
    fun `starts with seeded products`() = runBlocking {
        val products = repository.getAllProducts()

        assertEquals(4, products.size)
        assertEquals("Smartphone", products.first().name)
    }

    @Test
    fun `can add update and delete products`() = runBlocking {
        repository.addProduct(Product(name = "Mouse", price = 79.9, description = "Wireless mouse"))

        val added = repository.getProductById(5)
        assertNotNull(added)
        assertEquals("Mouse", added.name)

        val updated = repository.updateProduct(5, Product(name = "Mouse Pro", price = 99.9, description = null))
        assertEquals(5, updated.id)
        assertEquals("Mouse Pro", updated.name)

        repository.deleteProduct(5)
        assertNull(repository.getProductById(5))
    }
}

