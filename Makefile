# запуск программы из jar
run-dist:
	./build/install/java-project-lvl2/bin/java-project-lvl2 -f json file1.yaml file2.yaml
run-h:
	./build/install/java-project-lvl2/bin/java-project-lvl2 -h
# очистка и сборка build
build:
	./gradlew clean
	./gradlew installDist
#	./gradlew test
#	gradle check
check:
	gradle checkstyleMain
	gradle checkstyleTest
test:
	./gradlew test
# игнорирование папки Build. Без этого команда не работает
.PHONY: build