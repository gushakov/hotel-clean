package com.github.hotelclean.core;

import java.util.Collection;

public class Validator {

    public static <T> T notNull(T object) {
        if (object == null) {
            throw new InvalidDomainObjectError("Argument must not be null");
        }
        return object;
    }

    public static String notBlank(String text) {
        if (notNull(text).isBlank()) {
            throw new InvalidDomainObjectError("Argument must not be blank");
        }
        return text;
    }

    public static Integer strictlyPositive(Integer number) {
        if (number < 0) {
            throw new InvalidDomainObjectError("Argument must not be negative");
        }
        return number;
    }

    public static <T> Collection<T> notEmpty(Collection<T> items) {
        if (items == null || items.isEmpty()) {
            throw new InvalidDomainObjectError("Argument must not be null or empty");
        }
        return items;
    }

}
