package com.github.hotelclean.core.model.room;

import com.github.hotelclean.core.Validator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

/**
 * Aggregate root. Model of a hotel room.
 */
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Room {

    /**
     * Unique ID for this room.
     */
    @EqualsAndHashCode.Include
    RoomNumber roomNumber;

    /**
     * Beds this room is furnished with.
     */
    Set<Bed> beds;

    @Builder
    public Room(RoomNumber roomNumber, Set<Bed> beds) {
        this.roomNumber = Validator.notNull(roomNumber);
        this.beds = Validator.notEmpty(beds);
    }
}
