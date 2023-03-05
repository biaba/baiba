package com.skujevska.baiba.frontmodel;

import com.skujevska.baiba.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserFront {

    private static final String CONTAIN_LETTERS_MESSAGE = "Type must contain only letters";
    private static final String STRING_PATTERN = "^[a-zA-Z\\s]*$";

    private Long id;

    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = STRING_PATTERN, message = CONTAIN_LETTERS_MESSAGE)
    private String username;

    @NotBlank
    @Size(max = 120)
    private String password;

    private Set<Role> roles = new HashSet<>();

    public UserFront(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
