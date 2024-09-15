package by.ghoncharko.interview_training_platform.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "answer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id", nullable = false)
    private Long id;

    @Column(name = "answer_body", nullable = false, length = 1000)
    private String answerBody;

    @Column(name = "answer_true", nullable = false)
    private Boolean answerTrue = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

}