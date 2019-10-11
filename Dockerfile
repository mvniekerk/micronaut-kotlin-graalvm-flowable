FROM oracle/graalvm-ce:19.2.0.1 as graalvm
COPY . /home/app/queue-starter
WORKDIR /home/app/queue-starter
RUN gu install native-image
RUN native-image --no-server -cp build/libs/queue-starter-*-all.jar --no-server \
    -H:+ReportUnsupportedElementsAtRuntime -H:+ReportExceptionStackTraces \
    --enable-url-protocols=http,https \
    --initialize-at-run-time=io.netty.handler.ssl.util.BouncyCastleSelfSignedCertGenerator  \
    --initialize-at-run-time=io.netty.handler.ssl.JdkNpnApplicationProtocolNegotiator  \
    -H:EnableURLProtocols=http,https

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/queue-starter .
ENTRYPOINT ["./queue-starter"]
