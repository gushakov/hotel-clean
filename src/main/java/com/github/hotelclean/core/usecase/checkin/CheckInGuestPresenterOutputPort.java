package com.github.hotelclean.core.usecase.checkin;

import com.github.hotelclean.core.model.accommodation.RoomType;
import com.github.hotelclean.core.model.registration.Registration;
import com.github.hotelclean.core.model.registration.RegistrationId;
import com.github.hotelclean.core.model.room.Room;
import com.github.hotelclean.core.port.ErrorHandlingPresenterOutputPort;

import java.util.Set;

public interface CheckInGuestPresenterOutputPort extends ErrorHandlingPresenterOutputPort {

    void presentErrorIfNoAvailableRoomsMatchingRoomTypeDuringCheckIn(Registration registration, RoomType roomType);

    void presentAvailableMatchingRoomsForAssignment(RegistrationId registrationId, Set<Room> availableRooms);

    void presentErrorOnRoomTypeMismatchDuringAssignment(Registration registration, RoomType roomType,
                                                        Room room);

    void presentResultOfSuccessfulRoomAssignment(Registration registrationWithRoomAssigned);
}
