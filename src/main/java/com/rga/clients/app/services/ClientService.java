package com.rga.clients.app.services;

import com.rga.clients.app.dtos.ClientAndContactsDto;
import com.rga.clients.app.dtos.ClientDto;
import com.rga.clients.app.entities.Client;
import com.rga.clients.app.exceptions.ClientNotFoundException;
import com.rga.clients.app.mappers.ClientMapper;
import com.rga.clients.app.repositories.ClientRepository;
import com.rga.clients.app.utils.ClientSpecifications;
import com.rga.clients.app.utils.FilterCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ClientService {

    private final ClientRepository repository;

    private final ClientMapper mapper;

    @Transactional
    public ClientDto add(ClientDto clientDto) {
        var client = mapper.toClient(clientDto);
        var savedClient = repository.save(client);
        return mapper.toDto(savedClient);
    }

    protected Client findByIdOrThrowException(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ClientNotFoundException
                        (String.format("There is not any client with id %s", id)));
    }

    public ClientAndContactsDto findById(Long id) {
        var client = findByIdOrThrowException(id);
        return mapper.toDtoAndContacts(client);
    }

    public Page<ClientDto> findAll(FilterCriteria filters, Pageable pageable) {
        Specification<Client> spec = ClientSpecifications.createSpecifications(filters);
        return repository.findAll(spec, pageable).map(mapper::toDto);
    }

}
