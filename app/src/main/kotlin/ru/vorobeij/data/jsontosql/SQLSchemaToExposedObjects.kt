package ru.vorobeij.data.jsontosql

import java.nio.file.Files
import kotlin.io.path.Path

object SQLSchemaToExposedObjects {

    @JvmStatic
    fun main(args: Array<String>) {
        val converter = SQLLinesToFieldsConverter(100)

        val schemaPath = Path("/Users/sj/BackendApps/s-backend/src/main/resources/tables.sql")
        val outputPath = Path("/Users/sj/BackendApps/s-backend/src/main/kotlin/ru/vorobeij/backend/sub/database/tables/Tables.kt")
        val schema = Files.readString(schemaPath)
        val fixedSchema = converter.fixedSqlFile(schema)
        val res = converter.convertSqlFile(schema)
        println(res)
        Files.writeString(outputPath, res)
        Files.writeString(schemaPath, fixedSchema)
    }
}
