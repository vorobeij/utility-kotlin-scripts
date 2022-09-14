plugins {
    kotlin("jvm")

}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(KotlinX.serialization.json)
}

// https://github.com/autonomousapps/dependency-analysis-android-gradle-plugin/wiki/Customizing-plugin-behavior
dependencyAnalysis {
    issues {
        ignoreKtx(true)
        onAny {
            severity("fail")
            exclude(
                "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
            )
        }
    }
}

apply(from = "$rootDir/jacoco.gradle")
apply(plugin = "org.jetbrains.kotlin.plugin.serialization")