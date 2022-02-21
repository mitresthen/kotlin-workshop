package no.miles.kw

import io.ktor.application.*
import io.ktor.response.*

class Controller {

    suspend fun helloWorld(call: ApplicationCall){
        return call.respondText("Its working")
    }
}
