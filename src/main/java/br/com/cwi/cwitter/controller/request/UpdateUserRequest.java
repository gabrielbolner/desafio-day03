package br.com.cwi.cwitter.controller.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateUserRequest {


    private String fullName;

    private String profileImage;

    private String nickname;
}
