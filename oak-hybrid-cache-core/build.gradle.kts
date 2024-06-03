plugins {
  java
  id("org.springframework.boot") version "3.2.3"
  id("io.spring.dependency-management") version "1.1.4"
}

java {
  sourceCompatibility = JavaVersion.VERSION_21
  targetCompatibility = JavaVersion.VERSION_21
}


tasks.withType<JavaCompile>().configureEach() {
    options.encoding = "UTF-8"
}

group = "cn.xxywithpq"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val lombok_version = "1.18.30"
val caffeine_version = "3.1.8"
val fastjson2_version = "2.0.44"
val redisson_version = "3.31.0"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.projectlombok:lombok:$lombok_version")
    implementation("com.github.ben-manes.caffeine:caffeine:$caffeine_version")
    implementation("com.alibaba.fastjson2:fastjson2:$fastjson2_version")
    implementation("com.alibaba.cola:cola-component-exception:4.3.2")
    implementation("org.redisson:redisson-spring-boot-starter:$redisson_version")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    annotationProcessor ("org.projectlombok:lombok:$lombok_version")
}

tasks.test {
    useJUnitPlatform()
}