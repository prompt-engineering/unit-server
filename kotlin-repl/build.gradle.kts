import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.9"
	id("io.spring.dependency-management") version "1.1.0"
//	id("org.graalvm.buildtools.native") version "0.9.20"
	kotlin("jvm") version "1.8.20-Beta"
	kotlin("plugin.serialization") version "1.8.20-Beta"
	kotlin("plugin.spring") version "1.7.22"
	id("org.jetbrains.kotlin.jupyter.api") version "0.11.0-337"
	// kotlinx.serialization.SerializationException: Serializer for class 'InterpreterRequest' is not found.
}

group = "org.clickprompt"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
	maven(url = uri("https://packages.jetbrains.team/maven/p/ktls/maven"))
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

	implementation("javax.websocket:javax.websocket-api:1.1")
	implementation("org.springframework.boot:spring-boot-starter-websocket")

	implementation("org.jetbrains.kotlinx:kotlin-jupyter-api:0.11.0-337")
	implementation("org.jetbrains.kotlinx:kotlin-jupyter-kernel:0.11.0-337")

	// Serverless Kotlin Language Binding
	implementation("io.kotless:kotless-lang:0.2.0")
	implementation("io.kotless:kotless-lang-local:0.2.0")

	// Serverless Kotlin Spring
	implementation("io.kotless:spring-boot-lang:0.2.0")
	implementation("io.kotless:spring-boot-lang-local:0.2.0")
	implementation("io.kotless:spring-lang-parser:0.2.0")

	// Serverless Kotlin Ktor
	implementation("io.kotless:ktor-lang:0.2.0")
	implementation("io.kotless:ktor-lang-local:0.2.0")

	// Database ORM
	implementation("com.h2database:h2:2.1.212")
	implementation("org.jetbrains.exposed", "exposed-core", "0.40.1")
	implementation("org.jetbrains.exposed", "exposed-dao", "0.40.1")
	implementation("org.jetbrains.exposed", "exposed-jdbc", "0.40.1")

	// for REPL
	implementation("mysql:mysql-connector-java:8.0.32")
	implementation("org.jdbi:jdbi3-core:3.37.1")
	implementation("org.jdbi:jdbi3-kotlin-sqlobject:3.37.1")
	implementation("org.jdbi:jdbi3-kotlin:3.37.1")

	// tips: don't add follow deps to project will cause issues
	compileOnly("org.jetbrains.kotlin:kotlin-scripting-jvm:1.8.20-Beta")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation(libs.bundles.test)
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}