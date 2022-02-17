package no.miles.kw

import io.ktor.application.*
import io.ktor.response.*
import no.miles.kw.db.Shoe
import no.miles.kw.db.ShoeTableDao

class Controller(private val dao: ShoeTableDao) {

    suspend fun getAll(call: ApplicationCall) {
        val shoes = dao.get().map { "${it.name} - ${it.brand}"}
        return call.respondText("All the Shoes: $shoes")
    }

    suspend fun query(call: ApplicationCall) {
        val param = call.request.queryParameters["test"]
        val shoes = dao.get()
            .filter { it.brand == param }
            .map { "${it.name} - ${it.brand}"}
        return call.respondText("All the Shoes: $shoes")
    }

    suspend fun queryBySize(call: ApplicationCall){
        val shoeStorage = ShoeStorage()
        val shoes = dao.get()
        val res = shoeStorage.getShoeSizes(shoes)
        println(res)
        val sh = shoes.map{ "${it.name} - ${it.brand}"}
        return call.respondText("All the Shoes: $sh and $res")
    }

    private fun queryShoes(call: ApplicationCall): List<Shoe> {
        val param = call.request.queryParameters["test"]
        return dao.get()
            .filter { it.brand == param }
    }
}
