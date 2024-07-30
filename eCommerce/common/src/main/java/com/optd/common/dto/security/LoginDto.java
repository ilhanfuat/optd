package com.optd.common.dto.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;

}
