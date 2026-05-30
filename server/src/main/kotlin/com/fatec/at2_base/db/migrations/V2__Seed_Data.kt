package com.fatec.at2_base.db.migrations

import com.fatec.at2_base.db.Products
import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.insert

class V2__Seed_Data : BaseJavaMigration() {
    override fun migrate(context: Context) {
        val safeConnection = FlywayConnection(context.connection)
        val database = Database.connect({ safeConnection })

        transaction(database) {
            seedProducts()
        }
    }

    private fun seedProducts() {
        data class ProductData(
            val name: String, val description: String, val price: Double
        )

        val products = listOf(
            ProductData("Smartphone", "A high-end smartphone with a great camera", 999.99),
            ProductData("Laptop", "A powerful laptop for work and gaming", 1499.99),
            ProductData("Headphones", "Noise-cancelling over-ear headphones", 199.99),
            ProductData("Smartwatch", "A stylish smartwatch with fitness tracking", 299.99)
        )
        products.forEach { p ->
            Products.insert {
                it[name] = p.name
                it[description] = p.description
                it[price] = p.price
            }
        }
    }
}