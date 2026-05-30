package com.fatec.at2_base

import com.fatec.at2_base.db.DatabaseFactory
import com.fatec.at2_base.routes.productRoutes
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()
    routing {
        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }
        productRoutes(com.fatec.at2_base.repositories.ProductRepository())
    }
}