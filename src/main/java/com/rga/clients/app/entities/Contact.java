package com.rga.clients.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorColumn(name = "contact_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "contacts")
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long contactId;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "contact_type", insertable = false, updatable = false)
    private String contactType;

    @ManyToOne
    @JoinColumn(nullable = false, name = "client_id", referencedColumnName = "id")
    private Client client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(contactId, contact.contactId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(contactId);
    }
}
