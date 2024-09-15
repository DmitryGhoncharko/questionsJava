package by.ghoncharko.interview_training_platform.utils;

import by.ghoncharko.interview_training_platform.entity.Answer;
import by.ghoncharko.interview_training_platform.entity.Question;
import by.ghoncharko.interview_training_platform.repository.AnswerRepository;
import by.ghoncharko.interview_training_platform.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class CSVDatabaseUploader {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    @Transactional
    public void insertDataIntoDatabaseInTablesAnswerAndQuestion(String csvFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",", 2);  // Ожидаем две колонки: Вопрос и Ответ
                if (columns.length == 2) {
                    String question = columns[0].trim();
                    String answer = columns[1].trim();
                    Question questionAfterSave = questionRepository.save(Question.builder().questionBody(question).build());
                    answerRepository.save(Answer.builder().answerBody(answer).question(questionAfterSave).answerTrue(true).build());
                    log.info("Сохранен вопрос: " + questionAfterSave.getQuestionBody());
                    log.info("Сохранен ответ: " + answer);
                }
            }
            log.info("Загрузка данных в БД завершена !");
        } catch (IOException e) {
            log.error("Ошибка при чтении csv файла,  путь к фалу - " + csvFilePath + "\n сообщение с ошибкой - " + e.getMessage());
        }
    }
}
