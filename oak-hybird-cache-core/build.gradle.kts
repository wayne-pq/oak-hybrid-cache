plugins {
    id("java")
}

tasks.withType<JavaCompile>().configureEach() {
    options.encoding = "UTF-8"
}

group = "cn.xxywithpq"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val springboot_version = "3.2.1"
val lombok_version = "1.18.30"
val caffeine_version = "3.1.8"
val fastjson2_version = "2.0.44"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter:$springboot_version")
    implementation("org.springframework.boot:spring-boot-starter-data-redis:$springboot_version")
    implementation("org.springframework.boot:spring-boot-starter-cache:$springboot_version")
    implementation("org.projectlombok:lombok:$lombok_version")
    implementation("com.github.ben-manes.caffeine:caffeine:$caffeine_version")
    implementation("com.alibaba.fastjson2:fastjson2:$fastjson2_version")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springboot_version")
    annotationProcessor ("org.projectlombok:lombok:$lombok_version")
}

tasks.test {
    useJUnitPlatform()
}