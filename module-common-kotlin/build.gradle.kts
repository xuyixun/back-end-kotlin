plugins {
    kotlin("plugin.jpa")
}
dependencies {
    compileOnly("org.springframework.data:spring-data-jpa")
    compileOnly("org.hibernate:hibernate-core")
    compileOnly("com.fasterxml.jackson.core:jackson-databind")
    compileOnly("io.swagger:swagger-annotations:1.6.5")
}