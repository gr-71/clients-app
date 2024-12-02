package com.rga.clients.app.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {

    private Long id;

    @NotBlank
    private String clientName;
}
