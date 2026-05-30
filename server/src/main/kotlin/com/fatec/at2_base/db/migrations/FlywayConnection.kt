package com.fatec.at2_base.db.migrations

import java.sql.Connection

class FlywayConnection(private val delegate: Connection) : Connection by delegate {
    override fun close() {}
    override fun setTransactionIsolation(level: Int) {}
    override fun setAutoCommit(autoCommit: Boolean) {}
    override fun setReadOnly(readOnly: Boolean) {}
}