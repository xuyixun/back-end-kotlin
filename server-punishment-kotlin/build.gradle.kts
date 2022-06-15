plugins {
    kotlin("plugin.spring")
}
group = "com.xyx"
version = "0.0.1-SNAPSHOT"
dependencies {
    val springfoxVersion = "3.0.0"
    implementation(project(":module-security-kotlin"))
    implementation(project(":feature-service-punishment-kotlin"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    //implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("io.springfox:springfox-boot-starter:$springfoxVersion")
    runtimeOnly("mysql:mysql-connector-java")
}