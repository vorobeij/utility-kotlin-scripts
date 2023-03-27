package ru.vorobeij.midjourney

import java.io.File
import ru.vorobeij.midjourney.styles.isometricIllustration

fun main() {

    val paintObjects = """
        cute cat in a dark box
        cute robot searching with magnifying glass in desert with cactee
        broken videocamera in a desert
        red bug on a monitor
        red bug on a cactus in desert
        wind whirling sand in desert
        tumbleweed spinning in a desert with cactee
        men talking and translating
        pile of books with a cup of coffee
        Earth from space
        astronaut in space
        """.trimIndent()

    val styles = sortedMapOf(
        // "flatIllustration" to flatIllustration,
        "isometricIllustration" to isometricIllustration
    )

    val prompt = styles.keys.flatMap { styleName ->
        val style = styles[styleName]!!
        paintObjects
            .split("\n")
            .map { it.trim() }
            .mapIndexed { i, paintObject ->
                val prompt = prompt(style, paintObject)
                "${i + 1} $styleName\n$prompt\n"
            }
    }.joinToString("\n")
    File("prompt.txt").writeText(prompt)
}

private fun prompt(
    style: String,
    paintObject: String
): String {
    return PromptBuilder()
        .draw(paintObject)
        .styles(style)
        // .version(4)
        // .stylize(250)
        // .chaos(50)
        // .quality(".5")
        // .exclude("plants")
        // .style("4c")
        .build()
}
