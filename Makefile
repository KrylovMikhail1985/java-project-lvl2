# запуск программы из jar
run-dist:
	./build/install/java-project-lvl2/bin/java-project-lvl2 file1.json file2.json
# очистка и сборка build
build:
	./gradlew clean
	./gradlew installDist
check:
	gradle checkstyleMain
# игнорирование папки Build. Без этого команда не работает
.PHONY: build
