package com.rga.clients.app.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientAndContactsDto {

    private Long id;

    private String clientName;

    private List<ContactDto> contactList;
}
