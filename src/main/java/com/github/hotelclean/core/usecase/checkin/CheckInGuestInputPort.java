package com.github.hotelclean.core.usecase.checkin;

public interface CheckInGuestInputPort {

    void receptionistBrowsesAvailableRoomsMatchingRoomTypeFromReservation(String reservationIdArg);

    void receptionistAssignsRoom(String reservationIdArg, int roomNumberArg);

    // other check-in related activities, like issuing a set of keys, for example

}
