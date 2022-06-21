plugins {
    kotlin("plugin.spring")
}
dependencies {
    implementation(project(":module-common-kotlin"))
    implementation(project(":module-common-file-kotlin"))
    implementation(project(":module-security-base-kotlin"))
    implementation(project(":feature-domain-punishment-kotlin"))
    implementation(project(":module-security-base-kotlin"))
    compileOnly("org.springframework:spring-web")
    compileOnly("org.springframework.data:spring-data-jpa")
    compileOnly("org.springframework.security:spring-security-core")
    compileOnly("org.springframework.amqp:spring-rabbit")
    compileOnly("io.swagger:swagger-annotations:1.6.5")
    compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
}