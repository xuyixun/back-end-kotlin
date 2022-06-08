dependencies {
    val jjwtVersion = "0.11.5"
    implementation(project(":module-common-kotlin"))
    implementation(project(":module-security-base-kotlin"))
    compileOnly("org.springframework.security:spring-security-config")
    compileOnly("org.springframework.security:spring-security-web")
    compileOnly("org.springframework:spring-websocket")
    compileOnly("io.jsonwebtoken:jjwt-api:$jjwtVersion")
    compileOnly("org.springframework.data:spring-data-jpa")
    compileOnly("org.hibernate:hibernate-core")
    compileOnly("javax.servlet:javax.servlet-api")
    compileOnly("io.swagger:swagger-annotations:1.6.5")
}