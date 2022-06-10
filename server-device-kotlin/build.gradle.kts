plugins {
    kotlin("plugin.spring")
}
group = "com.example"
version = "0.0.1-SNAPSHOT"
dependencies {
    val springfoxVersion = "3.0.0"
    implementation(project(":module-security-kotlin"))
    implementation(project(":feature-service-device-kotlin"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    //implementation("org.springframework.boot:spring-boot-starter-actuator")

    //reactor
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    //r2dbc
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("dev.miku:r2dbc-mysql:0.8.2.RELEASE")
    //webflux_b
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("io.springfox:springfox-boot-starter:$springfoxVersion")
    runtimeOnly("mysql:mysql-connector-java")
}