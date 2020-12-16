import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.bmuschko.gradle.docker.tasks.image.Dockerfile

plugins {
	java
	idea
	val kotlinVersion = "1.4.21"
	id("org.springframework.boot") version "2.4.1"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	id("com.bmuschko.docker-spring-boot-application") version "6.6.1"
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
	kotlin("plugin.allopen") version kotlinVersion
	kotlin("plugin.noarg") version kotlinVersion
}

group = "blokaly.cloudaly"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenLocal()
	mavenCentral()
	jcenter()
}

configurations {
	all {
		exclude(module = "spring-boot-starter-logging")
		exclude(group="org.slf4j", module="slf4j-log4j12")
		exclude(group="org.apache.logging.log4j")
	}
}


dependencies {
	implementation("com.expediagroup:graphql-kotlin-spring-server:3.6.8")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// Logging
	implementation("org.slf4j:slf4j-api:1.7.30")
	implementation("ch.qos.logback:logback-classic:1.2.3")
	implementation("ch.qos.logback:logback-core:1.2.3")

	// Test
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
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

tasks.register<Copy>("copyDockerCompose") {
	from(file("devops/docker-compose.yml"))
	into(file("$buildDir/docker/"))
}

docker {
	springBootApplication {
		baseImage.set("amazoncorretto:11-alpine")
		ports.set(listOf(8080))
		jvmArgs.set(listOf("-Dspring.profiles.active=production"))
	}
}