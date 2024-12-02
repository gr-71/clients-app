package com.rga.clients.app.mappers;

import com.rga.clients.app.dtos.ContactDto;
import com.rga.clients.app.entities.Client;
import com.rga.clients.app.entities.Contact;
import com.rga.clients.app.entities.Email;
import com.rga.clients.app.entities.Phone;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Component
public class ContactMapper {

    public ContactDto toDto(Contact contact) {
        ContactDto dto = new ContactDto();
        dto.setContactId(contact.getContactId());
        dto.setText(contact.getText());
        dto.setContactType(contact.getContactType());
        return dto;
    }

    private List<ContactDto> toContactsListDto(List<Contact> contactList) {
        return contactList.stream().map(this::toDto).collect(Collectors.toList());
    }

    private List<Contact> toFullContactsList(Client client) {
        List<Contact> contactList = new ArrayList<>(client.getPhonesList());
        contactList.addAll(client.getEmailsList());
        return contactList;
    }

    public List<ContactDto> toContactsList(Client client) {
        return toContactsListDto(toFullContactsList(client));
    }

    public Contact toPhoneContact(ContactDto contactDto, Client client) {
        Contact phone = new Phone();
        phone.setText(contactDto.getText());
        phone.setClient(client);
        return phone;
    }

    public List<ContactDto> toPhonesList(Client client) {
        return toContactsListDto(new ArrayList<>(client.getPhonesList()));
    }

    public Contact toEmailContact(ContactDto contactDto, Client client) {
        Contact email = new Email();
        email.setText(contactDto.getText());
        email.setClient(client);
        return email;
    }

    public List<ContactDto> toEmailsList(Client client) {
        return toContactsListDto(new ArrayList<>(client.getEmailsList()));
    }

}
