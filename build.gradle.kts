plugins {
    java
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter")
// https://mvnrepository.com/artifact/jakarta.transaction/jakarta.transaction-api
//    implementation("jakarta.transaction:jakarta.transaction-api:2.0.1")


    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.13")
    implementation("org.hibernate.orm:hibernate-core:6.1.7.Final")

    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web")
    // https://mvnrepository.com/artifact/io.hypersistence/hypersistence-utils-hibernate-55
    // https://github.com/vladmihalcea/hypersistence-utils
    implementation("io.hypersistence:hypersistence-utils-hibernate-60:3.7.3")
    compileOnly("org.projectlombok:lombok")

    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("com.mysql:mysql-connector-j")

    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
