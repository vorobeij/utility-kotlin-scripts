package ru.vorobeij.midjourney

import java.io.File
import ru.vorobeij.midjourney.styles.flatIllustration
import ru.vorobeij.midjourney.styles.isometricIllustration

fun main() {

    val paintObject = """
        cat sitting in a box
        """.trimIndent()

    val styles = sortedMapOf(
        "flatIllustration" to flatIllustration,
        "isometricIllustration" to isometricIllustration
    )

    val prompt = styles.keys.joinToString("\n") { styleName ->
        val style = styles[styleName]!!
        val prompt = PromptBuilder()
            .draw(paintObject)
            .styles(style)
            // .version(4)
            .stylize(250)
            // .chaos(50)
            // .quality(".5")
            // .exclude("plants")
            .style("4c")
            .build()
        "$styleName\n$prompt\n"
    }
    File("prompt.txt").writeText(prompt)
}
