FROM openjdk:17-jdk-slim

# Установка git, curl, unzip и zip, а также bash
RUN apt-get update && apt-get install -y git curl unzip zip bash && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

# Устанавливаем рабочую директорию
WORKDIR /app

# Клонируем репозиторий и переключаемся на нужную ветку
RUN git clone https://github.com/DmitryGhoncharko/questionsJava.git . && \
    git checkout dev

# Установка SDKMAN и Gradle
RUN curl -s "https://get.sdkman.io" | bash && \
    bash -c "source ~/.sdkman/bin/sdkman-init.sh && sdk install gradle"

# Разрешение на выполнение для gradlew
RUN chmod +x gradlew

# Сборка проекта
RUN ./gradlew build -x test

# Запуск приложения
CMD ["./gradlew", "bootRun"]
