package br.com.cwi.cwitter.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(STRING)
    @NotNull
    private PostPrivacy postPrivacy;

    private String imageContent;

    private String writtenContent;

    @NotNull
    private LocalDateTime inclusionDate;

    private Long userId;
}