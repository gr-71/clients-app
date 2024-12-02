package com.rga.clients.app.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDto {

    private Long contactId;

    @NotBlank
    private String text;

    @NotBlank
    private String contactType;
}
