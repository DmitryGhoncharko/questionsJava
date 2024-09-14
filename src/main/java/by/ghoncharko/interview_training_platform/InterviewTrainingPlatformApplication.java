package by.ghoncharko.interview_training_platform;

import by.ghoncharko.interview_training_platform.utils.CSVReader;
import by.ghoncharko.interview_training_platform.utils.QuestionsParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InterviewTrainingPlatformApplication implements CommandLineRunner {
    @Autowired
    QuestionsParser questionsParser;
    @Autowired
    CSVReader csvReader;
    public static void main(String[] args) {
        SpringApplication.run(InterviewTrainingPlatformApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        csvReader.readCSV("/Users/dmitryghoncharko/Desktop/interview_questions/output.csv");
    }
}
