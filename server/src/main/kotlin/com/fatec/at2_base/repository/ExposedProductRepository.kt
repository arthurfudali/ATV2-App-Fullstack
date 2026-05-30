package com.fatec.at2_base.repository

import com.fatec.at2_base.db.Products
import com.fatec.at2_base.models.Product
import com.fatec.at2_base.repositories.ProductRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction


class ExposedProductRepository : ProductRepository {
    private fun ResultRow.toProduct() = Product(
        id = this[Products.id],
        name = this[Products.name],
        description = this[Products.description],
        price = this[Products.price]
    )

    override suspend fun getAllProducts(): List<Product> = newSuspendedTransaction {
        Products.selectAll().map { it.toProduct() }
    }

    override suspend fun getProductById(id: Int): Product? = newSuspendedTransaction {
        Products.selectAll().where { Products.id eq id }
            .map { it.toProduct() }
            .singleOrNull()
    }

    override suspend fun addProduct(product: Product) = newSuspendedTransaction {
        val insertStatement = Products.insert {
            it[name] = product.name
            it[description] = product.description
            it[price] = product.price
        }
        insertStatement.resultedValues!!.first().toProduct()
    }

    override suspend fun updateProduct(id: Int, product: Product): Product = newSuspendedTransaction {
        Products.update({ Products.id eq product.id }) {
            it[name] = product.name
            it[description] = product.description
            it[price] = product.price
        }
        Products.selectAll().where { Products.id eq product.id }
            .map { it.toProduct() }
            .single()
    }

    override suspend fun deleteProduct(id: Int): Unit = newSuspendedTransaction {
        Products.deleteWhere { Products.id eq id }
    }
}