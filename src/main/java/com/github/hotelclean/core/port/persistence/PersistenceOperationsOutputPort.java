package com.github.hotelclean.core.port.persistence;

import com.github.hotelclean.core.model.accommodation.RoomType;
import com.github.hotelclean.core.model.accommodation.RoomTypeId;
import com.github.hotelclean.core.model.registration.Registration;
import com.github.hotelclean.core.model.registration.RegistrationId;
import com.github.hotelclean.core.model.room.Room;

import java.time.LocalDate;
import java.util.Set;

public interface PersistenceOperationsOutputPort {
    Registration obtainRegistration(RegistrationId registrationId);

    RoomType obtainRoomType(RoomTypeId roomTypeId);

    Set<Room> findAvailableRooms(RoomType roomType, LocalDate registrationStartDate, LocalDate registrationEndDate);
}
