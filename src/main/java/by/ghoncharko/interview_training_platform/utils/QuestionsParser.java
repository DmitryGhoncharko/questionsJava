package by.ghoncharko.interview_training_platform.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@Slf4j
public class QuestionsParser {

    public void parseQuestionsAndAnswersToCSV(String directoryPath) {
        List<File> mdFiles = findMdFiles(directoryPath);
        if (mdFiles == null || mdFiles.isEmpty()) {
            log.info("Файлы .md не найдены.");
            return;
        }

        int questionCount = 0;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(directoryPath + "output.csv"))) {
            writer.write("Вопрос,Ответ\n");

            Pattern questionPattern = Pattern.compile("##\\s*\\d+\\.(.*)");

            for (File file : mdFiles) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    StringBuilder currentQuestion = new StringBuilder();
                    StringBuilder currentAnswer = new StringBuilder();
                    boolean readingQuestion = false;

                    while ((line = reader.readLine()) != null) {
                        Matcher questionMatcher = questionPattern.matcher(line);

                        if (questionMatcher.matches()) {
                            if (readingQuestion) {
                                writer.write(formatForCSV(currentQuestion.toString()) + "," + formatForCSV(currentAnswer.toString()) + "\n");
                                questionCount++;
                            }
                            currentQuestion = new StringBuilder(questionMatcher.group(1).trim());
                            currentAnswer = new StringBuilder();
                            readingQuestion = true;
                        } else {
                            if (readingQuestion) {
                                if (currentAnswer.length() > 0) {
                                    currentAnswer.append(" ");
                                }
                                currentAnswer.append(line.trim());
                            }
                        }
                    }

                    if (readingQuestion) {
                        writer.write(formatForCSV(currentQuestion.toString()) + "," + formatForCSV(currentAnswer.toString()) + "\n");
                        questionCount++;
                    }
                } catch (IOException e) {
                    log.error("Ошибка при парсинге в csv \n " +  e.getMessage(), e);
                }
            }

           log.info("Всего вопросов: " + questionCount);
        } catch (IOException e) {
            log.error("Ошибка при парсинге в csv \n " +  e.getMessage(), e);
        }
    }

    private List<File> findMdFiles(String directoryPath) {
        try {
            return Files.walk(Paths.get(directoryPath)).filter(Files::isRegularFile).filter(path -> path.toString().endsWith(".md")).map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            log.error("Не найдены файлы .md в директории " + directoryPath + "\n " + e.getMessage());
            return null;
        }
    }

    private String formatForCSV(String text) {
        if (text.contains(",") || text.contains("\"")) {
            text = text.replace("\"", "\"\"");
            return "\"" + text + "\"";
        }
        return text;
    }
}
