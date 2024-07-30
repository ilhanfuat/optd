package com.optd.common.dto.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class AuthResponseDto {

    @JsonProperty("user_id")
    Integer userId;

    @JsonProperty("username")
    String username;

    @JsonProperty("token")
    String token;

}
