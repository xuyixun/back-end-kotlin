dependencies {
    implementation(project(":module-common-kotlin"))
    implementation(project(":module-security-base-kotlin"))
    compileOnly("org.springframework:spring-web")
    compileOnly("org.springframework.security:spring-security-core")
}