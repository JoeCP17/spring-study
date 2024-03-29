import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("org.springframework.boot") version "2.6.3"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10" apply false
	kotlin("plugin.jpa") version "1.6.10" apply false
}

java.sourceCompatibility = JavaVersion.VERSION_11

allprojects {
	group = "com.kafka"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}
}

subprojects {
	apply(plugin = "java")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")

	apply(plugin = "kotlin")
	apply(plugin = "kotlin-spring") //all-open
	apply(plugin = "kotlin-jpa")

	dependencies {
		//spring boot
		implementation("org.springframework.boot:spring-boot-starter-data-jpa")
		implementation("org.springframework.boot:spring-boot-starter-web")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		developmentOnly("org.springframework.boot:spring-boot-devtools")
		implementation("org.springframework.boot:spring-boot-starter-validation") // kafka 에서 필요

		//kotlin
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

		//lombok
		compileOnly("org.projectlombok:lombok")
		annotationProcessor("org.projectlombok:lombok")

		//DB connect
		runtimeOnly("com.h2database:h2")
		runtimeOnly("mysql:mysql-connector-java")

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

project(":schedule") {
	dependencies {
		implementation("org.springframework.kafka:spring-kafka")
		implementation("io.github.microutils:kotlin-logging-jvm:2.0.10")
		implementation("org.slf4j:slf4j-api:1.7.30")

		testImplementation("org.springframework.kafka:spring-kafka-test")
	}
}

project(":schedule-data") {
	dependencies {
		implementation("org.slf4j:slf4j-api:1.7.30")
		// websocket
		implementation ("org.springframework.boot:spring-boot-starter-websocket")

		implementation ("org.springframework.boot:spring-boot-starter-webflux")
		implementation("org.springframework.kafka:spring-kafka")
		testImplementation("org.springframework.kafka:spring-kafka-test")
	}
}

project(":schedule") {
	val jar: Jar by tasks
	val bootJar: BootJar by tasks

	bootJar.enabled = false
	jar.enabled = true
}

project(":schedule-data") {
	val jar: Jar by tasks
	val bootJar: BootJar by tasks

	bootJar.enabled = false
	jar.enabled = true
}