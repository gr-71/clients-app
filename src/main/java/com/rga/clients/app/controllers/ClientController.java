package com.rga.clients.app.controllers;

import com.rga.clients.app.dtos.ClientAndContactsDto;
import com.rga.clients.app.dtos.ClientDto;
import com.rga.clients.app.services.ClientService;
import com.rga.clients.app.utils.FilterCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "clients", description = "Клиенты")
@RequestMapping("/rest/v1/clients")
@Validated
@AllArgsConstructor
@RestController
public class ClientController {

    private final ClientService service;

    @PostMapping
    @Operation(summary = "Add client",
            description = "Добавить нового клиента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClientDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content)
    })
    public ResponseEntity<ClientDto> addClient(@Valid @RequestBody ClientDto clientDto) {
        return ResponseEntity.ok(service.add(clientDto));
    }

    @GetMapping
    @Operation(summary = "Find all clients",
            description = "Получить список клиентов (по страницам)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content)
    })
    public ResponseEntity<Page<ClientDto>> findAllClients(@Valid FilterCriteria filters, Pageable pageable) {
        return ResponseEntity.ok(service.findAll(filters, pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find client by its id",
            description = "Найти клиента по его id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ClientAndContactsDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Not Found",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content)
    })
    public ResponseEntity<ClientAndContactsDto> findById(@NotNull @PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

}
