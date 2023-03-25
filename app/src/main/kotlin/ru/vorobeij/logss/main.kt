package ru.vorobeij.logss

import java.io.File

fun main() {
    // File("test.txt").writeText("skjdv")
    val logs = File("test.txt").readLines()
    val logKey = "playerdebug"
    val splitBy = "lkefjewfjn="

    val res = logs
        .filter { it.contains("$logKey") }
        .map { it.replace("^.*$logKey ".toRegex(), "") }
        .groupBy { it.split(splitBy)[1] }

    val f = File("out.txt")
    f.writeText("")
    res.keys.forEachIndexed { index, key ->
        val value = res[key]
        f.appendText("\n=========================\n")
        f.appendText("${index + 1} $key\n")
        f.appendText("=========================\n")
        f.appendText(
            value!!.joinToString("\n")
                .replace("$key", "")
                .replace(splitBy, "")
        )
    }
}
