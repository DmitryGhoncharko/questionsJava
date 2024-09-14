package by.ghoncharko.interview_training_platform.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
@Slf4j
public class CSVReader {

    public void readCSV(String csvFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            line = reader.readLine();
            System.out.println(line);

            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",", 2);  // Ожидаем две колонки: Вопрос и Ответ
                if (columns.length == 2) {
                    System.out.println("Вопрос: " + columns[0].trim());
                    System.out.println("Ответ: " + columns[1].trim());
                    System.out.println();
                }
            }
        } catch (IOException e) {
            log.error("Ошибка при чтении csv файла,  путь к фалу - " + csvFilePath + "\n сообщение с ошибкой - " + e.getMessage());
        }
    }
}
