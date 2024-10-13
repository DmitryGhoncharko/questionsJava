package by.ghoncharko.interview_training_platform.service;

import by.ghoncharko.interview_training_platform.entity.Question;
import by.ghoncharko.interview_training_platform.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Page<Question> findAll(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

}