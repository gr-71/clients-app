package com.rga.clients.app.controllers;

import com.rga.clients.app.dtos.ContactDto;
import com.rga.clients.app.services.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Tag(name = "contacts", description = "Контакты")
@RequestMapping("/rest/v1/clients/{id}/contacts")
@Validated
@AllArgsConstructor
@RestController
public class ContactController {

    private final ContactService service;

    @PostMapping
    @Operation(summary = "Add new contact for client with particular id.",
            description = "Добавление нового контакта клиента (телефон или email) для клиента с указанным id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ContactDto.class))),
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
    public ResponseEntity<ContactDto> addContact(@NotNull @PathVariable("id") Long id,
                                                 @Valid @RequestBody ContactDto contact) {
        return ResponseEntity.ok(service.add(id, contact));
    }

    @GetMapping
    @Operation(summary = "Find contacts for client with particular id.",
            description = "Получение списка всех контактов (либо контактов заданного типа) для клиента с указанным id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ContactDto.class))),
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
    public ResponseEntity<List<ContactDto>> findClientContacts(@NotNull @PathVariable("id") Long id,
                                                               @RequestParam(value = "type", required = false) String type) {
        return ResponseEntity.ok(service.getContactsForClient(id, type));
    }
}
