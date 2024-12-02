package com.rga.clients.app.entities;

import com.rga.clients.app.enums.ContactType;
import jakarta.persistence.*;

@DiscriminatorValue("email")
@Entity
public class Email extends Contact implements Contactable{

    @Override
    public String getContactType() {
        return ContactType.EMAIL.toString();
    }

}
