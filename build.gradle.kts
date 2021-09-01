/*
 * Copyright (C) 2019 Knot.x Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import org.nosphere.apache.rat.RatTask

group = "io.knotx"

plugins {
    id("io.knotx.java-library")
    id("io.knotx.codegen")
    id("io.knotx.unit-test")
    id("io.knotx.jacoco")
    id("io.knotx.maven-publish")
    id("io.knotx.release-java")
    id("org.nosphere.apache.rat")
}

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
        vendor.set(JvmVendorSpec.ADOPTOPENJDK)
    }
}
dependencies {
    annotationProcessor(platform("io.knotx:knotx-dependencies:${project.version}"))

    implementation(platform("io.knotx:knotx-dependencies:${project.version}"))
    implementation(group = "io.vertx", name = "vertx-core")
    implementation(group = "io.vertx", name = "vertx-rx-java2")

    implementation(group = "com.google.guava", name = "guava")
    implementation(group = "org.apache.commons", name = "commons-lang3")
    implementation(group = "org.junit.jupiter", name = "junit-jupiter-api")
    implementation(group = "org.junit.jupiter", name = "junit-jupiter-params")
    implementation(group = "org.junit.jupiter", name = "junit-jupiter-migrationsupport")

    testImplementation("io.knotx:knotx-junit5:${project.version}")
    testImplementation(group = "org.mockito", name = "mockito-core")
    testImplementation(group = "org.mockito", name = "mockito-junit-jupiter")
}

tasks {
    named<RatTask>("rat") {
        excludes.addAll(listOf(
            "**/*.md", // docs
            "gradle/wrapper/**", "gradle*", "**/build/**", // Gradle
            "*.iml", "*.ipr", "*.iws", "*.idea/**", // IDEs
            "**/generated/*", "**/*.adoc", "**/resources/**", // assets
            ".github/*", "conf/*.json", "conf/*.conf"
        ))
    }
    getByName("check").dependsOn("rat")
    getByName("rat").dependsOn("compileJava")
    getByName("sourcesJar").dependsOn("compileJava")
}

tasks {
    named<RatTask>("rat") {
        excludes.addAll(listOf(
            "**/*.md", // docs
            "gradle/wrapper/**", "gradle*", "**/build/**", // Gradle
            "*.iml", "*.ipr", "*.iws", "*.idea/**", // IDEs
            "**/generated/*", "**/*.adoc", "**/resources/**", // assets
            ".github/*"
        ))
    }
    getByName("check").dependsOn("rat")
    getByName("rat").dependsOn("compileJava")
}

publishing {
    publications {
        withType(MavenPublication::class) {
            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])
        }
    }
}
