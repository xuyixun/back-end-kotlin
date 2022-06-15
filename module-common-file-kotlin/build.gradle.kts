plugins {
    kotlin("plugin.jpa")
}
dependencies {
    implementation(project(":module-common-kotlin"))
    compileOnly("org.springframework.data:spring-data-jpa")
    compileOnly("org.hibernate:hibernate-core")
}