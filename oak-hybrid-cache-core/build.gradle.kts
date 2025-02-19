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
val cosidVersion = "2.9.4"
val rocketmq_client_version = "5.0.7"

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
    testAnnotationProcessor ("org.projectlombok:lombok:$lombok_version")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3")
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("com.google.guava:guava:33.2.1-jre")
    implementation("org.apache.shardingsphere:shardingsphere-jdbc:5.5.0"){
        exclude(group = "org.apache.shardingsphere", module = "shardingsphere-test-util")
    }
    implementation("org.glassfish.jaxb:jaxb-runtime:2.3.8")
    implementation ("cn.hutool:hutool-all:5.8.16")
    implementation("me.ahoo.cosid:cosid-jdbc:${cosidVersion}")
    implementation("me.ahoo.cosid:cosid-spring-boot-starter:${cosidVersion}")
    implementation("org.apache.rocketmq:rocketmq-client-java:${rocketmq_client_version}")
    }

tasks.test {
    useJUnitPlatform()
}