package com.github.hotelclean.core.usecase.checkin;

public interface CheckInGuestInputPort {

    void receptionistBrowsesAvailableRoomsMatchingRoomTypeFromRegistration(String registrationIdArg);

    void receptionistAssignsRoom(String registrationIdArg, int roomNumberArg);

    // other check-in related activities, like issuing a set of keys, for example

}
