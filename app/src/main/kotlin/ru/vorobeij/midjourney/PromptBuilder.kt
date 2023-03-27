package ru.vorobeij.midjourney

class PromptBuilder {

    private val draw = mutableListOf<String>()
    private val styles = mutableListOf<String>()
    private val arguments = mutableListOf<String>()

    fun styles(list: String) = this.apply {
        styles.addAll(list.lines().map { it.trim() })
    }

    fun draw(value: String) = this.apply {
        draw.addAll(value.lines().map { it.trim() })
    }

    fun stylize(value: Int) = this.apply {
        require(value in 0..1000)
        arguments.add("--stylize $value")
    }

    fun chaos(value: Int) = this.apply {
        require(value in 0..100)
        arguments.add("--chaos $value")
    }

    fun exclude(list: String) = this.apply {
        val items = list.lines().joinToString(" ") { it.trim() }
        arguments.add("--no $items")
    }

    /**
     * https://docs.midjourney.com/docs/models
     */
    fun style(value: String) = this.apply {
        arguments.add("--style $value")
    }

    fun quality(value: String) = this.apply {
        arguments.add("--quality $value")
    }

    fun creative() = this.apply {
        arguments.add("--test --creative")
    }

    fun version(v: Int) = this.apply {
        arguments.add("--v $v")
    }

    fun build(): String {
        val prompt = listOf(
            draw,
            styles
        ).map {
            it.distinct().filter { it.isNotBlank() && !it.startsWith("//") }.joinToString(", ")
        }.joinToString(", ")
        val args = arguments.distinct().joinToString(" ")
        return "/imagine prompt:$prompt $args"
    }
}
