package ru.vorobeij.data.jsontosql

class SQLLinesToFieldsConverter(
    private val charsDefaultLength: Int = 200
) {

    fun convertSqlFile(content: String): String {

        return """
            @file:Suppress("MemberVisibilityCanBePrivate")

            package ru.vorobeij.backend.sub.database.tables

            import org.jetbrains.exposed.sql.Column
            import org.jetbrains.exposed.sql.Table
            
            
        """.trimIndent() + content.split("\n\n")
            .filter { it.startsWith("CREATE") }
            .map {
                "/**\n${it.lines().map { it.prependIndent(" * ") }.joinToString("\n")}\n **/\n${convert(it)}"
            }
            .joinToString("\n\n")
    }

    fun fixedSqlFile(content: String): String {
        return content
            .replace("CREATE TABLE ", "CREATE TABLE IF NOT EXISTS ")
            .replace("create TABLE ", "CREATE TABLE IF NOT EXISTS ")
            .replace("\"", "")
    }

    fun convert(schema: String): String {
        val lines = schema.lines().toMutableList()
        val tableName = lines[0].split(" ")[2].trim('"')
        lines.removeAt(0)
        lines.removeAt(lines.lastIndex)

        val fields = lines.map(::lineToField)
            .flatten()
            .sortedBy(::fieldsOrdering)
            .joinToString("\n${"    ".repeat(1)}")
        return """
            |object ${tableName}Table : Table(name = "$tableName") {
            |
            |    $fields
            |}
        """.trim().trimMargin()
    }

    private fun fieldsOrdering(it: String): Int {
        return when {
            it.contains("PrimaryKey") -> 2
            else -> 0
        }
    }

    fun lineToField(line: String): List<String> {
        val items = line.trim().trim(',').split(" ")
        val name = items[0].trim('"')
        val type = items[1]
        val res = mutableListOf("val $name: ${typesMap(type, name)}")
        if (line.contains("PRIMARY KEY")) {
            val pk = """override val primaryKey = PrimaryKey($name)"""
            res.add(pk)
        }
        return res
    }

    /**
     * https://github.com/JetBrains/Exposed/wiki/DataTypes
     */
    private fun typesMap(
        type: String,
        name: String
    ): String {
        val simpleType = mapOf(
            "varchar" to """Column<String> = varchar("$name", $charsDefaultLength)""",
            "INTEGER" to """Column<Long> = long("$name")""",
            "int" to """Column<Long> = long("$name")""",
            "boolean" to """Column<Boolean> = bool("$name")""",
            "TSVECTOR" to """String = TODO()""",
            "SERIAL" to """Column<Int> = integer("id").autoIncrement()"""
        )[type]
        return when {
            simpleType != null -> simpleType
            Regex("varchar\\(\\d+\\)").matches(type) -> {
                val size = Regex("\\d+").find(type)!!.value
                """Column<String> = varchar("$name", $size)"""
            }

            else -> "TODO()"
        }
    }
}