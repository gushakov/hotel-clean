package com.github.hotelclean.core.model.accommodation;

import com.github.hotelclean.core.Validator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

/**
 * Occupancy (maximum number of persons) allowed by a particular
 * accommodation.
 */
@Value
public class Occupancy {

    @Getter(AccessLevel.NONE)
    int persons;

    public static Occupancy of(int persons) {
        return new Occupancy(persons);
    }

    @Builder
    public Occupancy(int persons) {
        this.persons = Validator.strictlyPositive(persons);
    }

    public int asInteger() {
        return persons;
    }
}
