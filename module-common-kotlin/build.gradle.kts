plugins {
    kotlin("jvm")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("plugin.jpa")
}
java.sourceCompatibility = JavaVersion.VERSION_17
dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compileOnly("io.swagger:swagger-annotations:1.6.5")
    compileOnly("org.springframework.data:spring-data-jpa")
    compileOnly("org.hibernate:hibernate-core")
}