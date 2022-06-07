plugins {
    val kotlinVersion = "1.6.21"
    id("org.springframework.boot") version "2.7.0" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
    id("org.jetbrains.kotlin.jvm") version kotlinVersion apply false
    kotlin("plugin.spring") version kotlinVersion apply false
    kotlin("plugin.jpa") version kotlinVersion apply false
}
subprojects {
    repositories {
        maven(url = "https://maven.aliyun.com/repository/public/")
        mavenLocal()
        mavenCentral()
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }
    tasks.withType<Test> {
        useJUnitPlatform()
    }
}