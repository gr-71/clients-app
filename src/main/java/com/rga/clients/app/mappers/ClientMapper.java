package com.rga.clients.app.mappers;

import com.rga.clients.app.dtos.ClientAndContactsDto;
import com.rga.clients.app.dtos.ClientDto;
import com.rga.clients.app.dtos.ContactDto;
import com.rga.clients.app.entities.Client;
import com.rga.clients.app.entities.Contact;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ClientMapper {

    private final ContactMapper contactMapper;

    public ClientDto toDto(Client client) {
        ClientDto dto = new ClientDto();
        dto.setId(client.getId());
        dto.setClientName(client.getClientName());
        return dto;
    }

    public ClientAndContactsDto toDtoAndContacts(Client client) {
        ClientAndContactsDto dto = new ClientAndContactsDto();
        dto.setId(client.getId());
        dto.setClientName(client.getClientName());
        dto.setContactList(contactMapper.toContactsList(client));
        return dto;
    }

    public Client toClient(ClientDto dto) {
        Client client = new Client();
        client.setClientName(dto.getClientName());
        return client;
    }

}
