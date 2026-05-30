package com.fatec.at2_base.db.migrations

import com.fatec.at2_base.db.Products
import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class V1__Initial_Schema : BaseJavaMigration() {
    override fun migrate(context: Context) {
        val safeConnection = FlywayConnection(context.connection)
        val database = Database.connect({ safeConnection })

        transaction(database) {
            // A prioridade e precedência ditam o uso das Chaves Estrangeiras
            SchemaUtils.create(Products)
        }
    }
}