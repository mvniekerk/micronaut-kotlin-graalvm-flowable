# micronaut-kotlin-graalvm-flowable
Micronaut, Kotlin, GraalVM and Flowable. Not compiling natively. Yet.

To run:
`./gradlew run`

To build a native image (and fail):
`./gradlew clean shadowJar && docker build -t testing .`
