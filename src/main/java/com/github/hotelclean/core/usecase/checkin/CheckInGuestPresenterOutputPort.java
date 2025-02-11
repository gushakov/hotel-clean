package com.github.hotelclean.core.usecase.checkin;

import com.github.hotelclean.core.model.accommodation.RoomType;
import com.github.hotelclean.core.model.registration.Registration;
import com.github.hotelclean.core.model.registration.RegistrationId;
import com.github.hotelclean.core.model.room.Room;
import com.github.hotelclean.core.port.ErrorHandlingPresenterOutputPort;

import java.util.Set;

public interface CheckInGuestPresenterOutputPort extends ErrorHandlingPresenterOutputPort {

    void presentErrorIfNoAvailableRoomsMatchingRoomTypeAtCheckIn(Registration registration, RoomType roomType);

    void presentAvailableRoomsForAssignment(RegistrationId registrationId, Set<Room> availableRooms);
}
