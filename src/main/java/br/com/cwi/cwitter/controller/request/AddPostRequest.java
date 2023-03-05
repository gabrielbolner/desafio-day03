package br.com.cwi.cwitter.controller.request;

import br.com.cwi.cwitter.domain.PostPrivacy;
import lombok.*;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddPostRequest {

    @NotNull
    @Enumerated(STRING)
    private PostPrivacy postPrivacy;

    private String imageContent;

    private String writtenContent;

    @NotNull
    private LocalDateTime inclusionDate;
    @NotNull
    private Long userId;
}