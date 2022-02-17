package no.miles.kw

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import no.miles.kw.db.Shoe
import no.miles.kw.db.ShoeTableDao
import org.h2.jdbcx.JdbcDataSource
import org.jetbrains.exposed.sql.Database
import javax.sql.DataSource

const val fileName = "/shoes.txt"

fun main() {
    val server = Services()
    server.nettyServer.start(wait = true)
}

class Services {

    private val db = Db()
    private val controller: Controller = Controller(db.dao)

    init {
        db.insertData()
    }

    val nettyServer = embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                call.respondText("Hello World")
            }
            get("/shoes") {
                println(call.request.queryParameters["test"])
                controller.query(call)
            }
            get("/sizes"){
                controller.queryBySize(call)
            }
        }
    }
}

class Db {

    private val datasource: DataSource = createH2Datasource()
    val dao = ShoeTableDao(datasource)

    init {
        dao.createTable()
        val connect = Database.connect(datasource)
        println("Connected to $connect")
    }

    fun insertData(){
        println("Gonna insert data")
        val fileContent = Db::class.java.getResourceAsStream(fileName).bufferedReader().readLines()
        fileContent
            .filter { !it.startsWith("#") }
            .forEach{
                val splitted = it.split(";")
                dao.insert(Shoe(splitted[0].trim(), splitted[1].trim()))
        }
    }

    private fun createH2Datasource() =
        JdbcDataSource().apply {
            setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")
            user = "sa"
            password = "sa"
        }
}