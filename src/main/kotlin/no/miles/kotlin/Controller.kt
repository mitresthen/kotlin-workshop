package no.miles.kotlin

import io.ktor.application.*
import io.ktor.response.*
import no.miles.kotlin.db.ShoeTableDao

class Controller(private val dao: ShoeTableDao) {

    suspend fun getAll(call: ApplicationCall) {
        val shoes = dao.get().map { "${it.name} - ${it.type}"}
        return call.respondText("All the Shoes: $shoes")
    }
}
