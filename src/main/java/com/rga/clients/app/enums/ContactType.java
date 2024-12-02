package com.rga.clients.app.enums;

import com.rga.clients.app.exceptions.UnknownContactTypeException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum ContactType {

    PHONE("phone"),
    EMAIL("email");

    private final String value;

    public static ContactType getTypeFromValue(String value) {
        return Arrays.stream(values())
                .filter(ct -> ct.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(()-> new UnknownContactTypeException
                        (String.format("There is not any contact type with value %s", value)));
    }
}
