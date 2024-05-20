plugins {
    id("java")
    kotlin("jvm")
    id("xyz.jpenilla.run-paper") version "2.3.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "de.alaust.frostblossommc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.reflections:reflections:0.10.2")

    compileOnly("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks {
    test {
        useJUnitPlatform()
    }
}
kotlin {
    jvmToolchain(21)
}