package ru.vorobeij.data.jsontosql

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SqlTable(
    @SerialName("database") val database: Database,
    @SerialName("formatVersion") val formatVersion: Int
) {

    @Serializable
    data class Database(
        @SerialName("entities") val entities: List<Entity>,
        @SerialName("identityHash") val identityHash: String,
        @SerialName("setupQueries") val setupQueries: List<String>,
        @SerialName("version") val version: Int
    ) {

        @Serializable
        data class Entity(
            @SerialName("createSql") val createSql: String,
            @SerialName("fields") val fields: List<Field>,
            @SerialName("foreignKeys") val foreignKeys: List<ForeignKey>,
            @SerialName("indices") val indices: List<Indice>,
            @SerialName("primaryKey") val primaryKey: PrimaryKey,
            @SerialName("tableName") val tableName: String
        ) {

            @Serializable
            data class Field(
                @SerialName("affinity") val affinity: String,
                @SerialName("columnName") val columnName: String,
                @SerialName("defaultValue") val defaultValue: String? = null,
                @SerialName("fieldPath") val fieldPath: String,
                @SerialName("notNull") val notNull: Boolean
            )

            @Serializable
            data class ForeignKey(
                @SerialName("columns") val columns: List<String>,
                @SerialName("onDelete") val onDelete: String,
                @SerialName("onUpdate") val onUpdate: String,
                @SerialName("referencedColumns") val referencedColumns: List<String>,
                @SerialName("table") val table: String
            )

            @Serializable
            data class Indice(
                @SerialName("columnNames") val columnNames: List<String>,
                @SerialName("createSql") val createSql: String,
                @SerialName("name") val name: String,
                @SerialName("unique") val unique: Boolean
            )

            @Serializable
            data class PrimaryKey(
                @SerialName("autoGenerate") val autoGenerate: Boolean,
                @SerialName("columnNames") val columnNames: List<String>
            )
        }
    }
}