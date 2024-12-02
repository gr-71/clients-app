package com.rga.clients.app.utils;

import com.rga.clients.app.entities.Client;
import com.rga.clients.app.entities.Client_;
import com.rga.clients.app.entities.Contact;
import com.rga.clients.app.entities.Contact_;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ClientSpecifications {

    public static Specification<Client> createSpecifications(FilterCriteria criteria) {
        return idEqualsTo(criteria.getId())
                .and(nameEqualsTo(criteria.getName()))
                .and(phoneIsIn(criteria.getPhone()))
                .and(emailIsIn(criteria.getEmail()));
    }

    public static Specification<Client> idEqualsTo(Long id) {
        return (root, query, builder) ->
                id == null ? null : builder.equal(root.get(Client_.id), id);
    }

    public static Specification<Client> nameEqualsTo(String name) {
        return (root, query, builder) ->
                name == null ? null : builder.equal(root.get(Client_.clientName), name);
    }

    public static Specification<Client> phoneIsIn(String phone) {
        return (root, query, builder) -> {
            Join<Client, Contact> phoneJoin = root.join(Client_.phonesList);
            return phone == null ? null : phoneJoin.get(Contact_.text).in(List.of(phone));
        };
    }

    public static Specification<Client> emailIsIn(String email) {
        return (root, query, builder) -> {
            Join<Client, Contact> emailJoin = root.join(Client_.emailsList);
            return email == null ? null : emailJoin.get(Contact_.text).in(List.of(email));
        };
    }

}
