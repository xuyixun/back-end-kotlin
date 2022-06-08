plugins {
    kotlin("plugin.spring")
}
group = "com.example"
version = "0.0.1-SNAPSHOT"
dependencies {
    val springfoxVersion = "3.0.0"
    implementation(project(":module-security-kotlin"))
    implementation(project(":feature-test-kotlin"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("io.springfox:springfox-boot-starter:$springfoxVersion")
    runtimeOnly("com.microsoft.sqlserver:mssql-jdbc")
}