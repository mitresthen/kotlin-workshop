package no.miles.kotlin.db

import org.jetbrains.exposed.sql.Table

object ShoeTable : Table("SHOETABLE") {
    val name = varchar("NAME", 255)
    val type = varchar("TYPE", 255)

    override val primaryKey: PrimaryKey = PrimaryKey(name, name = "${tableName}_pk")
}