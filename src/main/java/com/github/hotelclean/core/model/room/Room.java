package com.github.hotelclean.core.model.room;

import com.github.hotelclean.core.Validator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
    Collection<Bed> beds;

    /**
     * Indicates whether this room is available for guests, i.e,
     * not reserved for maintenance.
     */
    boolean availableForGuests;

    /**
     * Indicates whether this room is occupied (by a guest or by
     * a staff member).
     */
    boolean occupied;

    @Builder
    public Room(RoomNumber roomNumber, Collection<Bed> beds, Boolean availableForGuests, Boolean occupied) {
        this.roomNumber = Validator.notNull(roomNumber);
        this.beds = List.copyOf(Validator.notEmpty(beds));
        // room is available by default
        this.availableForGuests = Optional.ofNullable(availableForGuests).orElse(true);
        // room is not occupied by default
        this.occupied = Optional.ofNullable(occupied).orElse(false);
    }

    /**
     * Marks this room as occupied.
     *
     * @return a copy of this room with {@code occupied} set to {@code true}
     */
    public Room occupy() {
        return newRoom()
                .occupied(true)
                .build();
    }

    private RoomBuilder newRoom() {
        return Room.builder()
                .roomNumber(roomNumber)
                .beds(beds)
                .availableForGuests(availableForGuests)
                .occupied(occupied);
    }
}
