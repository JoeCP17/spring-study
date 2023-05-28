import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10" apply false
    kotlin("plugin.jpa") version "1.6.10" apply false}

group = "org.example"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

allprojects {
    group = "org.example"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "kotlin-spring") //all-open

    dependencies {
        //spring boot
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        //kotlin
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        //lombok
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")

        //test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    dependencyManagement {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }

        dependencies {
            dependency("net.logstash.logback:logstash-logback-encoder:6.6")
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }
}

project("infra-common"){

}

project(":infra-domain") {
    apply(plugin = "kotlin-jpa")

    dependencies {
        project(":infra-common:common-model")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    }
}

project(":infra-infrastructure:persistence-database") {
    apply(plugin = "kotlin-jpa")

    dependencies {
        project(":infra-common:common-model")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")

        //DB connect
        runtimeOnly("com.h2database:h2")
        runtimeOnly("mysql:mysql-connector-java")
    }
}

project(":infra-infrastructure:persistence-redis-adapter") {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-redis")

        // https://mvnrepository.com/artifact/it.ozimov/embedded-redis
        implementation ("it.ozimov:embedded-redis:0.7.3")

        // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
        implementation ("com.fasterxml.jackson.core:jackson-databind:2.14.1")
    }
}

project(":infra-interface") {
    apply(plugin = "org.springframework.boot")

    dependencies {
        project(":infra-domain")
        project(":infra-common:common-model")
        project(":infra-common:common-util")

        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation ("org.springframework.boot:spring-boot-starter-aop")
        implementation("org.springframework.boot:spring-boot-starter-validation")


    }
}