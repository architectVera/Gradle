import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootRun

plugins {
  	id("org.springframework.boot") version "3.1.0"
  	id("io.spring.dependency-management") version "1.1.0"
  	kotlin("jvm") version "1.8.21"
  	kotlin("plugin.spring") version "1.8.21"
	kotlin("plugin.serialization") version "1.8.21"
}

group = "academy.softserve"
version = "0.0.1-SNAPSHOT"
java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	classpath("org.springframework.boot:spring-boot-gradle-plugin:3.1.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
    	jvmTarget = "17"
	}
}

tasks.register<Exec>("npmInstall") {
    dependsOn("KotlinCompile")
    workingDir = file("ui")
    commandLine("npm", "i")
}

tasks.register<Exec>("compileUi") {
    dependsOn("npmInstall")
    workingDir = file("ui")
    commandLine("npm", "run", "build")
}

tasks.register<Copy>("copyUi") {
    dependsOn("compileUi")
    from("ui/dist") {
        include("index.html")
        include("app.js")
        into("css") {
            from("css")
        }
    }
    into("src/main/resources")
}

tasks.withType<BootRun> {
    dependsOn(copyUi)
}




// TODO: your code starts here