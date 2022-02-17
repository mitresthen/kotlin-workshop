package no.miles.kw.db

import org.jetbrains.exposed.sql.Table

object ShoeTable : Table("SHOETABLE") {
    val name = varchar("NAME", 255)
    val brand = varchar("BRAND", 255)

    override val primaryKey: PrimaryKey = PrimaryKey(name, name = "${tableName}_pk")
}