plugins {
    kotlin("plugin.jpa")
}
dependencies {
    implementation(project(":module-common-kotlin"))
    compileOnly("org.springframework.data:spring-data-jpa")
    compileOnly("org.hibernate:hibernate-core")
    compileOnly("org.springframework:spring-web")
    compileOnly("io.swagger:swagger-annotations:1.6.5")
}