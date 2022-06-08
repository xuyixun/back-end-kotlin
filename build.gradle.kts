plugins {
    val kotlinVersion = "1.6.21"
    `java-library`
    id("org.springframework.boot") version "2.7.0" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
    id("org.jetbrains.kotlin.jvm") version kotlinVersion apply false
    kotlin("plugin.spring") version kotlinVersion apply false
    kotlin("plugin.jpa") version kotlinVersion apply false
}
subprojects {
    apply(plugin ="org.jetbrains.kotlin.jvm")
    apply(plugin ="org.springframework.boot")
    apply(plugin ="io.spring.dependency-management")
    repositories {
        maven(url = "https://maven.aliyun.com/repository/public/")
        mavenLocal()
        mavenCentral()
    }
    dependencies {
        val guavaVersion = "31.1-jre"
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("com.google.guava:guava:$guavaVersion")
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