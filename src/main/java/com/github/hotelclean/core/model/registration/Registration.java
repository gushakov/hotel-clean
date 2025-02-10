package com.github.hotelclean.core.model.registration;

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
 * Aggregate root. Registration of a guest for a particular accommodation
 * type and for a particular period of time. Registration is associated
 * with the room number of an actual room assigned to a guest during
 * the guest's stay the hotel.
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Registration {

    /**
     * Unique ID for this registration.
     */
    @EqualsAndHashCode.Include
    RegistrationId id;

    /**
     * Full name of the guest who made the reservation.
     */
    String guestName;

    /**
     * ID of the room type for this registration.
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
    public Registration(RegistrationId id, String guestName, RoomTypeId roomTypeId, LocalDate startDate, LocalDate endDate,
                        RoomNumber assignedRoomNumber) {
        this.id = Validator.notNull(id);
        this.guestName = Validator.notBlank(guestName);
        this.roomTypeId = Validator.notNull(roomTypeId);
        this.startDate = Validator.notNull(startDate);
        this.endDate = Validator.notNull(endDate);

        // will be "null" before check-in and after check-out
        this.assignedRoomNumber = assignedRoomNumber;
    }


    private Registration.RegistrationBuilder newRegistration() {
        return Registration.builder()
                .id(id)
                .guestName(guestName)
                .roomTypeId(roomTypeId)
                .startDate(startDate)
                .endDate(endDate)
                .assignedRoomNumber(assignedRoomNumber);

    }
}
