package com.example.finances.mappers;

import com.example.finances.DTO.ClientDTO;
import com.example.finances.models.Client;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ClientMapper {
    public ClientDTO toDto(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .lastName(client.getLastName())
                .firstName(client.getFirstName())
                .middleName(client.getMiddleName())
                .build();
    }
}
