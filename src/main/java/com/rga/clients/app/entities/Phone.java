package com.rga.clients.app.entities;

import com.rga.clients.app.enums.ContactType;
import jakarta.persistence.*;

@DiscriminatorValue("phone")
@Entity
public class Phone extends Contact implements Contactable{

    @Override
    public String getContactType() {
        return ContactType.PHONE.toString();
    }
}
