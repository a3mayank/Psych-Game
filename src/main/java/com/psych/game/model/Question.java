package com.psych.game.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "questions")
public class Question extends Auditable {
    @NotBlank
    @Getter
    @Setter
    private String question;

    @NotBlank
    @Getter
    @Setter
    private String correctAnswer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    @Getter
    @Setter
    private Set<EllenAnswer> ellenAnswer = new HashSet<>();

    @Enumerated(EnumType.STRING)
//    @ManyToOne
    @NotNull
    @Getter
    @Setter
    private GameMode gameMode;
}
