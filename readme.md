# Что реализовано
* Подключен ликвибейз для наката схемы и таблиц
* Добавлены утилитные классы для парсинга csv с исходником вопросов и парсинга md с вопросами в csv
* Добавлен утилитный класс для наката данных на БД
* uploadCsv -PcsvFile=/Users/dmitryghoncharko/IdeaProjects/interview_training_platform/src/main/resources/questions/output.csv добавлена новая таска гредл которая совершает накат, после наката необходимо прогнать скрипт доделки в ресурсах delete_invalid_data_from_question_and_answer_tables.sql
* В таблицах answer и question отсутствуют индексы на полях text нужно быть внимательным и избегать запросов по полю text