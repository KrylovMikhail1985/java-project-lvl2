# запуск программы из jar
run-dist:
	./build/install/java-project-lvl2/bin/java-project-lvl2
# очистка и сборка build
build:
	./gradlew clean
	./gradlew installDist
# игнорирование папки Build. Без этого команда не работает
.PHONY: build
