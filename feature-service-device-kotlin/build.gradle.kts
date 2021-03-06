dependencies {
    implementation(project(":module-common-kotlin"))
    implementation(project(":module-security-base-kotlin"))
    implementation(project(":feature-domain-device-kotlin"))
    compileOnly("org.springframework:spring-web")
    compileOnly("org.springframework:spring-webflux")
    compileOnly("org.springframework.data:spring-data-jpa")
    compileOnly("org.springframework.data:spring-data-r2dbc")
    compileOnly("org.springframework.security:spring-security-core")
    compileOnly("io.swagger:swagger-annotations:1.6.5")
}