package com.github.hotelclean.core.model.accommodation;

import com.github.hotelclean.core.Validator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

/**
 * Unique ID of a room type.
 */
@Value
public class RoomTypeId {

    @Getter(AccessLevel.NONE)
    int id;

    public static RoomTypeId of(int id) {
        return new RoomTypeId(id);
    }

    @Builder
    public RoomTypeId(int id) {
        this.id = Validator.strictlyPositive(id);
    }

    public int asInteger(){
        return id;
    }
}
