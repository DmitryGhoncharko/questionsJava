package by.ghoncharko.interview_training_platform.repository;

import by.ghoncharko.interview_training_platform.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
