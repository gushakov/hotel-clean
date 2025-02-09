package com.github.hotelclean.core;

import java.util.Set;

public class Validator {

    public static <T> T notNull(T object) {
        if (object == null) {
            throw new InvalidDomainObjectError("Argument must not be null");
        }
        return object;
    }

    public static Integer strictlyPositive(Integer number) {
        if (number < 0) {
            throw new InvalidDomainObjectError("Argument must not be negative");
        }
        return number;
    }

    public static <T> Set<T> notEmpty(Set<T> items) {
        if (items == null || items.isEmpty()) {
            throw new InvalidDomainObjectError("Argument must not be null or empty");
        }
        return items;
    }

}
