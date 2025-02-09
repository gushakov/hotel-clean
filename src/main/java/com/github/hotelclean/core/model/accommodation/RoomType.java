package com.github.hotelclean.core.model.accommodation;

import com.github.hotelclean.core.Validator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * Aggregate root. Describes a type or a category of rooms available
 * at the hotel.
 */

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RoomType {

    /**
     * Unique ID of this room type.
     */
    RoomTypeId id;

    String name;

    @Builder
    public RoomType(RoomTypeId id, String name) {
        this.id = Validator.notNull(id);
        this.name = Validator.notNull(name);
    }
}
