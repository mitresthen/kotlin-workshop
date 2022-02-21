package no.miles.kw

import com.google.gson.GsonBuilder
import io.ktor.application.*
import io.ktor.response.*

class Controller {

    private val gson = GsonBuilder().setPrettyPrinting().create()

    suspend fun helloWorld(call: ApplicationCall){
        return call.respondText(toJson("Its working"))
    }

    private fun toJson(theObjet: Any) = gson.toJson(theObjet)
}
