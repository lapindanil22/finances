package com.example.finances.DTO.responses;

import com.example.finances.DTO.ClientDTO;

import java.util.List;

public class ClientResponse {
    private List<ClientDTO> clients;

    public ClientResponse(List<ClientDTO> clients){
        this.clients = clients;
    }

    public List<ClientDTO> getAllClients() {
        return clients;
    }

    public void setClients(List<ClientDTO> client) {
        this.clients = client;
    }
}
