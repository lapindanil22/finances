package com.example.finances.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDTO {
    private Integer id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String secretWord;
}
