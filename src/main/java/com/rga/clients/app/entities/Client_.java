package com.rga.clients.app.entities;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Client.class)
public class Client_ {
    public static volatile SingularAttribute<Client, Long> id;
    public static volatile SingularAttribute<Client, String> clientName;
    public static volatile ListAttribute<Client, Contact> phonesList;
    public static volatile ListAttribute<Client, Contact> emailsList;
}
