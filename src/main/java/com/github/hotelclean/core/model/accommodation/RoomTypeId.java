package com.github.hotelclean.core.model.accommodation;

import com.github.hotelclean.core.Validator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

/**
 * ID of a room type (unique within our hotel).
 */
@Value
public class RoomTypeId {

    @Getter(AccessLevel.NONE)
    String id;

    public static RoomTypeId of(String id) {
        return new RoomTypeId(id);
    }

    @Builder
    public RoomTypeId(String id) {
        this.id = Validator.notBlank(id);
    }

    public String asString() {
        return id;
    }
}
