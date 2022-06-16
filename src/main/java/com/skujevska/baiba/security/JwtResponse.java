package com.skujevska.baiba.security;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class JwtResponse {
    private String token;
    private String type;
    private Long id;
    private String username;
    private List<String> roles;
}
