package com.github.hotelclean.core.model.room;

import com.github.hotelclean.core.Validator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

/**
 * Room number of a hotel room. This number will be used
 * as the identifier for each room in our application.
 */
@Value
public class RoomNumber {

    @Getter(AccessLevel.NONE)
    int number;

    public static RoomNumber of(int number) {
        return new RoomNumber(number);
    }

    @Builder
    public RoomNumber(int number) {
        this.number = Validator.strictlyPositive(number);
    }

    public int asInteger() {
        return number;
    }

    public String asString() {
        return String.valueOf(number);
    }
}
