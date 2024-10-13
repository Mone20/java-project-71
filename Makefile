setup:
	gradle wrapper --gradle-version 8.5

clean:
	./app/gradlew clean


build:
	./app/gradlew clean build

run:
	./app/gradlew run --args="-h"

lint:
	./app/gradlew checkstyleMain checkstyleTest

test:
	./app/gradlew test

report:
	./app/gradlew jacocoTestReport

install:
	./app/gradlew clean install

run-dist:
	./app/build/install/app/bin/app -h

check-updates:
	./app/gradlew dependencyUpdates

.PHONY: build
