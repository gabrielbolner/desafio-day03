package br.com.cwi.cwitter.controller.request;

import lombok.*;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddCommentRequest {

    @NotNull
    private String comment;

    @NotNull
    private Long userId;
}
