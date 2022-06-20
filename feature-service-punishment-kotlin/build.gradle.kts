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
    compileOnly("io.swagger:swagger-annotations:1.6.5")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
}