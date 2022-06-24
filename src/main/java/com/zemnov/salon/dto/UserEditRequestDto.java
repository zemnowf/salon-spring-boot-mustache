package com.zemnov.salon.dto;

import com.zemnov.salon.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEditRequestDto {

    private String username;
    private Map<String, String> form;
    private String clientName;
    private String number;
    private String mail;

}
