plugins {
    kotlin("jvm")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}
dependencies {
    val jjwtVersion = "0.11.5"
    implementation(project(":module-common-kotlin"))
    implementation("io.jsonwebtoken:jjwt-api:$jjwtVersion")
    compileOnly("org.springframework.security:spring-security-core")
    compileOnly("org.yaml:snakeyaml")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:$jjwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jjwtVersion")
}