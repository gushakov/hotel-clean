package com.github.hotelclean.core.usecase.checkin;

import com.github.hotelclean.core.model.accommodation.RoomType;
import com.github.hotelclean.core.model.reservation.Reservation;
import com.github.hotelclean.core.model.reservation.ReservationId;
import com.github.hotelclean.core.model.room.Room;
import com.github.hotelclean.core.port.ErrorHandlingPresenterOutputPort;

import java.util.Set;

public interface CheckInGuestPresenterOutputPort extends ErrorHandlingPresenterOutputPort {

    void presentErrorIfNoAvailableRoomsMatchingRoomTypeDuringCheckIn(Reservation reservation, RoomType roomType);

    void presentAvailableMatchingRoomsForAssignment(ReservationId reservationId, Set<Room> availableRooms);

    void presentErrorOnRoomTypeMismatchDuringAssignment(Reservation reservation, RoomType roomType,
                                                        Room room);

    void presentResultOfSuccessfulRoomAssignment(Reservation reservationWithRoomAssigned);
}
