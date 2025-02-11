package com.github.hotelclean.core.model;

import com.github.hotelclean.core.model.accommodation.RoomType;
import com.github.hotelclean.core.model.room.Bed;
import com.github.hotelclean.core.model.room.Room;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class DefaultRoomTypeMatchingSpecification implements RoomTypeMatchingSpecification {
    RoomType roomType;

    @Override
    public boolean roomTypeMatches(Room room) {

        /*
            POINT OF INTEREST
            -----------------
            We should implement here the more precise algorithm
            which matches a given room with the room type provided
            at construction-time of this specification. As an example,
            we simply compare the occupancy of the room type with
            the sum of all beds' capacities of the room.
         */

        return room.getBeds().stream()
                .map(Bed::occupancy)
                .reduce(0, Integer::sum) >= roomType.getOccupancy().asInteger();
    }
}
