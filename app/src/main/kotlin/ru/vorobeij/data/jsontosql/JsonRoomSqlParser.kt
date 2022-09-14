package ru.vorobeij.data.jsontosql

import java.io.File
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/**
 * Parse Room schema json to sql
 */
object JsonRoomSqlParser {

    private val jsonConfig = Json {
        ignoreUnknownKeys = true
    }

    fun parseRoomJsonToSql(jsonFile: File): String {
        val json = jsonFile.readText()
        val table = jsonConfig.decodeFromString<SqlTable>(json)
        val sql = StringBuilder()
        sql.append(table.database.setupQueries.joinToString(";\n"))
        sql.append("\n")
        sql.append(table.database.entities.joinToString(";\n") { it.createSql.replace("`\${TABLE_NAME}`", it.tableName) })
        return sql.toString()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val sql = parseRoomJsonToSql(File("schema.json"))
        File("res.sql").writeText(sql)
    }
}