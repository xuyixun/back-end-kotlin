FROM gradle:alpine AS build
WORKDIR /workspace
COPY . .
RUN gradle :server-punishment-kotlin:build
RUN mkdir -p server-punishment-kotlin/build/dependency && (cd server-punishment-kotlin/build/dependency; jar -xf ../libs/server-punishment-kotlin-0.0.1-SNAPSHOT.jar)

FROM openjdk:17
ARG DEPENDENCY=/workspace/server-punishment-kotlin/build/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-noverify","-cp","app:app/lib/*","com.xyx.AppKt"]