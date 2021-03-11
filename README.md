# Spring Native Demo

This project is for demonstrating the usage of Spring Native for compiling Spring Boot applications
to native executables using GraalVM native-image compiler.

Check the [official documentation](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/)
for more information about the project.

## Dependencies

* Spring Boot
* Spring WebFlux
* Spring Data R2DBC
* Spring Security
* Lombok

## How to run it

Run PostgreSQL as a Docker container:

```bash
docker run --name db-native \
  -e POSTGRES_USER=admin \
  -e POSTGRES_PASSWORD=admin \
  -e POSTGRES_DB=db_native \
  -p 5432:5432 \
  -d postgres:13
```

Build the Spring Boot application as a native GraalVM image:

```bash
./gradlew bootBuildImage
```

Run the native image:

```bash
docker run --name spring-native-graalvm-demo -p 8080:8080 spring-native-graalvm-demo:1.0.0
```

Call the unauthenticated application REST endpoint:

```bash
http :8080
```

Call the authenticated application REST endpoint:

```bash
http :8080/books --auth sheldon:password
```
