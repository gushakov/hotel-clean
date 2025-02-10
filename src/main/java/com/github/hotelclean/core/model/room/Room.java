package com.github.hotelclean.core.model.room;

import com.github.hotelclean.core.Validator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.Optional;
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
     * Beds this room is furnished with. This a simple collection,
     * since we can have duplicates: i.e.: two double beds, etc.
     */
    Collection<Bed> beds;

    /**
     * Indicates whether this room is available for guests.
     */
    boolean availableForGuests;

    @Builder
    public Room(RoomNumber roomNumber, Set<Bed> beds, Boolean availableForGuests) {
        this.roomNumber = Validator.notNull(roomNumber);
        // room is available by default
        this.availableForGuests = Optional.ofNullable(availableForGuests).orElse(true);
        this.beds = Validator.notEmpty(beds);

    }
}
