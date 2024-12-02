package com.rga.clients.app.services;

import com.rga.clients.app.dtos.ContactDto;
import com.rga.clients.app.entities.Client;
import com.rga.clients.app.entities.Contact;
import com.rga.clients.app.enums.ContactType;
import com.rga.clients.app.exceptions.ContactInvalidException;
import com.rga.clients.app.exceptions.UnknownContactTypeException;
import com.rga.clients.app.mappers.ContactMapper;
import com.rga.clients.app.repositories.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class ContactService {

    private final ContactRepository repository;

    private final ContactMapper mapper;

    private final ClientService clientService;

    @Transactional
    public ContactDto add(Long id, ContactDto contactDto) {
        Client client = clientService.findByIdOrThrowException(id);
        Contact contact = defineContact(contactDto, client);
        repository.save(contact);
        return mapper.toDto(contact);
    }

    public List<ContactDto> getContactsForClient(Long id, String type) {
        Client client = clientService.findByIdOrThrowException(id);
        if (type != null) {
            ContactType contactType = ContactType.getTypeFromValue(type);
            switch (contactType) {
                case PHONE -> {
                    return mapper.toPhonesList(client);
                }
                case EMAIL -> {
                    return mapper.toEmailsList(client);
                }
                default -> {
                    return mapper.toContactsList(client);
                }
            }
        } else {
            return mapper.toContactsList(client);
        }
    }

    private boolean validateContact(ContactDto contactDto) {
        if (contactDto.getText().equalsIgnoreCase("PHONE")) {
            String phoneRegexPattern = "^((\\+7|7|8)+([0-9]){10})$";
            return contactDto.getText().matches(phoneRegexPattern);
        } else if (contactDto.getText().equalsIgnoreCase("EMAIL")) {
            String emailRegexPattern = "^(\\S+)@([a-z0-9-]+)(\\.)([a-z]{2,4})(\\.?)([a-z]{0,4})+$";
            return contactDto.getText().matches(emailRegexPattern);
        } else {
            throw new ContactInvalidException
                    (String.format("Contact with value %s is invalid", contactDto.getText()));
        }
    }

    private Contact defineContact(ContactDto contactDto, Client client) {
        ContactType type = ContactType.getTypeFromValue(contactDto.getContactType());
        if (validateContact(contactDto)) {
            switch (type) {
                case PHONE -> {
                    return mapper.toPhoneContact(contactDto, client);
                }
                case EMAIL -> {
                    return mapper.toEmailContact(contactDto, client);
                }
            }
        }
        throw new UnknownContactTypeException
                (String.format("There is unknown contact type: %s", type));
    }

}
