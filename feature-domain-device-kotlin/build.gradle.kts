plugins {
    kotlin("plugin.jpa")
}
dependencies {
    implementation(project(":module-common-kotlin"))
    implementation(project(":module-common-file-kotlin"))
    compileOnly("org.springframework.data:spring-data-jpa")
    compileOnly("org.springframework.data:spring-data-r2dbc")
    compileOnly("org.hibernate:hibernate-core")
    compileOnly("io.swagger:swagger-annotations:1.6.5")
}