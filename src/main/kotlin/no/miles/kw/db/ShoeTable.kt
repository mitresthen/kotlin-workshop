package no.miles.kw.db

import org.jetbrains.exposed.sql.Table

object ShoeTable : Table("SHOETABLE") {
    val id = integer("id").autoIncrement()
    val name = varchar("NAME", 255)
    val brand = varchar("BRAND", 255)

    override val primaryKey: PrimaryKey = PrimaryKey(id, name = "${tableName}_pk")
}