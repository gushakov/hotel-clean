package com.github.hotelclean.core.model;

import com.github.hotelclean.core.model.room.Room;

/**
 * Specification used to determine if a room matches a room type.
 */
public interface RoomTypeMatchingSpecification {

    boolean roomTypeMatches(Room room);

}
