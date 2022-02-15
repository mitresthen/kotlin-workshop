package no.miles.kotlin

import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import no.miles.kotlin.db.Shoe
import no.miles.kotlin.db.ShoeTableDao
import org.h2.jdbcx.JdbcDataSource
import org.jetbrains.exposed.sql.Database
import javax.sql.DataSource

fun main() {
    val server = Services()
    server.nettyServer.start(wait = true)
}

class Services() {

    private val db = Db()
    private val controller: Controller = Controller(db.dao)

    init {
        db.insertData()
    }

    val nettyServer = embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                controller.getAll(call)
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
        dao.insert(Shoe("Test data", "Cowboy boots"))
    }

    private fun createH2Datasource() =
        JdbcDataSource().apply {
            setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")
            user = "sa"
            password = "sa"
        }
}