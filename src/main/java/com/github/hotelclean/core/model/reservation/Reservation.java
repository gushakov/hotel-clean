package com.github.hotelclean.core.model.reservation;

import com.github.hotelclean.core.Validator;
import com.github.hotelclean.core.model.accommodation.RoomTypeId;
import com.github.hotelclean.core.model.room.RoomNumber;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

/**
 * Aggregate root. Reservation of a guest for a particular accommodation
 * type and for a particular period of time. Reservation is associated
 * with the room number of an actual room assigned to a guest during
 * the guest's stay the hotel.
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reservation {

    /**
     * Unique ID for this reservation.
     */
    @EqualsAndHashCode.Include
    ReservationId id;

    /**
     * Full name of the guest who made the reservation.
     */
    String guestName;

    /**
     * ID of the room type for this reservation.
     */
    RoomTypeId roomTypeId;

    /**
     * Start date of the stay at the hotel.
     */
    LocalDate startDate;

    /**
     * End date of the stay at the hotel;
     */
    LocalDate endDate;

    /**
     * Number of the room assigned to the guest at check-in.
     */
    RoomNumber assignedRoomNumber;

    @Builder
    public Reservation(ReservationId id, String guestName, RoomTypeId roomTypeId, LocalDate startDate, LocalDate endDate,
                       RoomNumber assignedRoomNumber) {
        this.id = Validator.notNull(id);
        this.guestName = Validator.notBlank(guestName);
        this.roomTypeId = Validator.notNull(roomTypeId);
        this.startDate = Validator.notNull(startDate);
        this.endDate = Validator.notNull(endDate);

        // will be "null" before check-in and after check-out
        this.assignedRoomNumber = assignedRoomNumber;

        checkInvariants();
    }

    /**
     * Assigns a room with the given {@code roomNumber} to this reservation.
     *
     * @param roomNumber room number
     * @return a copy of this reservation with room number assigned
     */
    public Reservation assignRoom(RoomNumber roomNumber) {
        return newReservation()
                .assignedRoomNumber(assignedRoomNumber)
                .build();
    }

    private ReservationBuilder newReservation() {
        return Reservation.builder()
                .id(id)
                .guestName(guestName)
                .roomTypeId(roomTypeId)
                .startDate(startDate)
                .endDate(endDate)
                .assignedRoomNumber(assignedRoomNumber);

    }

    private void checkInvariants() {

        // check dates for the reservation
        if (startDate.isAfter(endDate)) {
            throw new IllegalReservationDatesError("Start date cannot be after end date");
        }

    }
}
