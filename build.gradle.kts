plugins {
    java
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
}

val axonVersion = "4.8.3"
val axonExtensionKafkaVersion = "4.8.0"
val axonExtensionSpringCloudVersion = "4.8.0"

allprojects {
    group = "com.example"
    version = "0.0.1-SNAPSHOT"

    apply(plugin = "java")

    java {
        sourceCompatibility = JavaVersion.VERSION_21
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.functionaljava:functionaljava:5.0")
        implementation("com.fasterxml.jackson.core:jackson-annotations:2.13.5")
        implementation("com.fasterxml.jackson.core:jackson-core:2.13.5")
        implementation("com.fasterxml.jackson.core:jackson-databind:2.13.5")
        compileOnly("org.projectlombok:lombok:1.18.30")
        annotationProcessor("org.projectlombok:lombok:1.18.30")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

project(":appkit:application-basic") {
    dependencies {
        implementation("org.slf4j:slf4j-api:2.0.0")
    }
}

project (":axon:application") {
    dependencies {
        implementation(project(":appkit:application-basic"))
        implementation("org.axonframework:axon-spring-boot-starter:$axonVersion")
    }
}

project (":doc-service:doc-api") {
    dependencies {
        implementation(project(":appkit:application-basic"))
        implementation(project(":doc-service:doc-shared"))
        implementation("org.axonframework:axon-spring-boot-starter:$axonVersion")
    }
}

project (":doc-service:doc-shared") {
    dependencies {
        implementation(project(":appkit:application-basic"))
    }
}

project (":doc-service:doc-web") {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation(project(":appkit:application-basic"))
        implementation(project(":axon:application"))
        implementation(project(":doc-service:doc-api"))
        implementation(project(":doc-service:doc-shared"))

        implementation("org.axonframework:axon-spring-boot-starter:$axonVersion")
        implementation("org.axonframework.extensions.kafka:axon-kafka-spring-boot-starter:$axonExtensionKafkaVersion")
        implementation("org.axonframework.extensions.springcloud:axon-springcloud-spring-boot-starter:$axonExtensionSpringCloudVersion")
        implementation("org.apache.kafka:kafka-clients:3.2.2")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

        runtimeOnly("com.h2database:h2")
        runtimeOnly("com.mysql:mysql-connector-j")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}

project (":payment-service:payment-api") {
    dependencies {
        implementation(project(":appkit:application-basic"))
        implementation(project(":payment-service:payment-shared"))
        implementation(project(":user-service:user-shared"))
        implementation("org.axonframework:axon-spring-boot-starter:$axonVersion")
    }
}

project (":payment-service:payment-shared") {
    dependencies {
        implementation(project(":appkit:application-basic"))
    }
}

project (":payment-service:payment-web") {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation(project(":appkit:application-basic"))
        implementation(project(":axon:application"))
        implementation(project(":payment-service:payment-api"))
        implementation(project(":payment-service:payment-shared"))
        implementation(project(":user-service:user-sdk"))
        implementation(project(":user-service:user-shared"))

        implementation("org.axonframework:axon-spring-boot-starter:$axonVersion")
        implementation("org.axonframework.extensions.kafka:axon-kafka-spring-boot-starter:$axonExtensionKafkaVersion")
        implementation("org.axonframework.extensions.springcloud:axon-springcloud-spring-boot-starter:$axonExtensionSpringCloudVersion")
        implementation("org.apache.kafka:kafka-clients:3.2.2")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

        runtimeOnly("com.h2database:h2")
        runtimeOnly("com.mysql:mysql-connector-j")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}

project(":pubsubdoc-service:back") {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation(project(":appkit:application-basic"))
        implementation(project(":doc-service:doc-api"))
        implementation(project(":doc-service:doc-shared"))
        implementation(project(":pubsubdoc-service:back-api"))
        implementation(project(":user-service:user-api"))
        implementation(project(":user-service:user-sdk"))
        implementation(project(":user-service:user-shared"))

        implementation("org.axonframework:axon-spring-boot-starter:$axonVersion")
        implementation("org.axonframework.extensions.kafka:axon-kafka-spring-boot-starter:$axonExtensionKafkaVersion")
        implementation("org.axonframework.extensions.springcloud:axon-springcloud-spring-boot-starter:$axonExtensionSpringCloudVersion")
        implementation("org.apache.kafka:kafka-clients:3.2.2")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

        runtimeOnly("com.h2database:h2")
        runtimeOnly("com.mysql:mysql-connector-j")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}

project (":pubsubdoc-service:back-api") {
    dependencies {
        implementation(project(":appkit:application-basic"))
        implementation(project(":user-service:user-shared"))
        implementation("org.axonframework:axon-spring-boot-starter:$axonVersion")
    }
}

project (":user-service:user-api") {
    dependencies {
        implementation(project(":appkit:application-basic"))
        implementation(project(":user-service:user-shared"))
        implementation("org.axonframework:axon-spring-boot-starter:$axonVersion")
    }
}

project (":user-service:user-shared") {
    dependencies {
        implementation(project(":appkit:application-basic"))
    }
}

project (":user-service:user-sdk") {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation(project(":appkit:application-basic"))
        implementation("org.springframework.boot:spring-boot-starter-web")
    }
}

project (":user-service:user-web") {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation(project(":appkit:application-basic"))
        implementation(project(":axon:application"))
        implementation(project(":payment-service:payment-api"))
        implementation(project(":payment-service:payment-shared"))
        implementation(project(":pubsubdoc-service:back-api"))
        implementation(project(":user-service:user-api"))
        implementation(project(":user-service:user-shared"))

        implementation("org.axonframework:axon-spring-boot-starter:$axonVersion")
        implementation("org.axonframework.extensions.kafka:axon-kafka-spring-boot-starter:$axonExtensionKafkaVersion")
        implementation("org.axonframework.extensions.springcloud:axon-springcloud-spring-boot-starter:$axonExtensionSpringCloudVersion")
        implementation("org.apache.kafka:kafka-clients:3.2.2")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

        runtimeOnly("com.h2database:h2")
        runtimeOnly("com.mysql:mysql-connector-j")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}