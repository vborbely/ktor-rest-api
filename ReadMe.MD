# Ktor Rest API sample

## Basics

This sample project is for creating a simple User table. For the simplicity it is utilizes __H2__ in-memory Database.

## Architecture

The project is written in Ktor Kotlin web services. It mimics the well-known Spring Boot project layered architecture.

```
project-root
  |
  +---- config : configurations; DB; DI
  +---- domain : data converters between DB and APIs
  |  |
  |  +---- dto
  |  +---- entities
  |
  +---- plugins
  +---- repositories : services use repo's
  +---- services : APIs access services
  +---- utils : helper classes
```

## Packages

__Dealing with JSON__

implementation("io.ktor:ktor-serialization:$ktor_version")
implementation("io.ktor:ktor-jackson:$ktor_version")

__Dependency Injection__

implementation("io.insert-koin:koin-ktor:$koin_version")

__Database layer__

implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
implementation("com.h2database:h2:1.4.200")

__Input validation__

implementation("org.valiktor:valiktor-core:0.12.0"

Checks the validity of the DTO data, that comes from the user.

__HTTP Requests__

There is a predefined file with HTTP requests in: `api/http_requests.http`

__Authentication/Authorization__

Not implemented yet. JWT infrastructure planned later. Ktor has build-in support for JWT and JWK.

__Build__

The build.grade file is configured to build a fat JAR. It is good for Docker deployments.

The shadowJar package is used for this.

```
$  ./gradlew shadowJar

$  java -jar build/libs/ktor-rest-api-x.y.z-all.jar
```

__Run the project__

`$ ./gradlew run`

__Error handling__

The Exceptions are mapped to standard `ErrorDTO` format.
