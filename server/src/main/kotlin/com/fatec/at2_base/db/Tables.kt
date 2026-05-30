package com.fatec.at2_base.db
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object Products : IntIdTable() {
    val name = varchar("name", 255)
    val price = double("price")
    val description = text("description").nullable()
}