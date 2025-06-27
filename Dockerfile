FROM gradle:8-jdk17 AS build
WORKDIR /app
COPY . .
# Install Node.js 18
RUN curl -fsSL https://deb.nodesource.com/setup_18.x | bash - \
 && apt-get install -y nodejs \
 && ./gradlew distZip

FROM openjdk:17-jdk-slim
WORKDIR /opt
COPY --from=build /app/build/distributions/yanagishima-*.zip yanagishima.zip
RUN apt-get update && apt-get install -y unzip \
 && unzip yanagishima.zip \
 && mv yanagishima-* yanagishima \
 && rm yanagishima.zip \
 && apt-get purge -y unzip && rm -rf /var/lib/apt/lists/*
WORKDIR /opt/yanagishima
CMD ["bin/yanagishima-start.sh"]
