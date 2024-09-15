package by.ghoncharko.interview_training_platform;

import by.ghoncharko.interview_training_platform.utils.CSVDatabaseUploader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class InterviewTrainingPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterviewTrainingPlatformApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ApplicationContext context) {
        return args -> {
            if (args.length > 0) {
                CSVDatabaseUploader uploader = context.getBean(CSVDatabaseUploader.class);
                uploader.insertDataIntoDatabaseInTablesAnswerAndQuestion(args[0]);
            } else {
                log.error("Укажите путь к CSV файлу в аргументах!");
            }
        };
    }
}
