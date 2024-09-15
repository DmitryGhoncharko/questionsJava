package by.ghoncharko.interview_training_platform.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "question_type")
public class QuestionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_type_id", nullable = false)
    private Long id;

    @Column(name = "question_type_name", nullable = false, length = 600)
    private String questionTypeName;

}