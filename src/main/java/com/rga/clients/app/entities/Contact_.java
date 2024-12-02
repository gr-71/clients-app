package com.rga.clients.app.entities;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Contact.class)
public class Contact_ {
    public static volatile SingularAttribute<Contact, Long> contactId;
    public static volatile SingularAttribute<Contact, String> text;
    public static volatile SingularAttribute<Contact, String> contactType;
    public static volatile SingularAttribute<Contact, Client> client;
}
