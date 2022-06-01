# запуск программы из jar
run-dist:
	./build/install/java-project-lvl2/bin/java-project-lvl2 /home/krylov_mikhail/java-project-lvl2/src/file1.json /home/krylov_mikhail/java-project-lvl2/src/file2.json
# очистка и сборка build
build:
	./gradlew clean
	./gradlew installDist
# игнорирование папки Build. Без этого команда не работает
.PHONY: build
