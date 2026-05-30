package com.fatec.at2_base.routes

import com.fatec.at2_base.models.Product
import com.fatec.at2_base.repositories.ProductRepository
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post

fun Route.productRoutes(productRepository: ProductRepository) {

    get("/products") {
        val products = productRepository.getAllProducts()
        call.respond(products)
    }
    get("/products/{id}") {
        val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respondText(
            "Invalid ID",
            status = io.ktor.http.HttpStatusCode.BadRequest
        )
        val product = productRepository.getProductById(id) ?: return@get call.respondText(
            "Product not found",
            status = io.ktor.http.HttpStatusCode.NotFound
        )
        call.respond(product)
    }
    post("/products") {
        val product = call.receive<Product>()
        productRepository.addProduct(product)
        call.respondText("Product added successfully", status = io.ktor.http.HttpStatusCode.Created)
    }

}