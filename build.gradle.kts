plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.plugin.serialization)
}

group = "com.example"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.postgresql)
    implementation(libs.h2)

    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.kotlin.datetime)
    implementation(libs.exposed.json)

    // JasperReports - minimal setup
    implementation("net.sf.jasperreports:jasperreports:6.21.5") {
        // Exclude problematic transitive dependencies
        exclude(group = "com.lowagie", module = "itext")
        exclude(group = "eclipse")
    }

    implementation("org.xerial:sqlite-jdbc:3.44.1.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.19.+")

    // PDF support
    implementation("com.github.librepdf:openpdf:1.3.30")

    // Excel support
    implementation("org.apache.poi:poi:5.2.4")
    implementation("org.apache.poi:poi-ooxml:5.2.4")

    // XML processing (sometimes needed)
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.15.2")

    // Fonts
    implementation(files("fonts/jasperreports-fonts.jar"))

    implementation(libs.ktor.server.host.common)
    implementation(libs.ktor.server.status.pages)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.config.yaml)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)
}
