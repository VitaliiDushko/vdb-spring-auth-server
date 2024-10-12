plugins {
	java
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.vdb"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(22)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-authorization-server")
	implementation("org.projectlombok:lombok:1.18.34")
	annotationProcessor("org.projectlombok:lombok:1.18.34")
	implementation("org.postgresql:postgresql:42.7.3")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.postgresql:postgresql:42.7.3")
	implementation("org.mapstruct:mapstruct:1.6.0")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.6.0")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
