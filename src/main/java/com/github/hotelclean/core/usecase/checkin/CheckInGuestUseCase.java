package com.github.hotelclean.core.usecase.checkin;

import com.github.hotelclean.core.model.accommodation.RoomType;
import com.github.hotelclean.core.model.registration.Registration;
import com.github.hotelclean.core.model.registration.RegistrationId;
import com.github.hotelclean.core.model.room.Room;
import com.github.hotelclean.core.port.persistence.PersistenceOperationsOutputPort;
import com.github.hotelclean.core.port.securirty.SecurityOperationsOutputPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CheckInGuestUseCase implements CheckInGuestInputPort {

    CheckInGuestPresenterOutputPort presenter;
    SecurityOperationsOutputPort securityOps;
    PersistenceOperationsOutputPort persistenceOps;

    /*
        POINT OF INTEREST
        -----------------
        This would probably evolve to contain other arguments, as to
        allow the receptionist to specify other criteria, i.e.: floor
        preference, ect.
     */

    @Override
    public void receptionistBrowsesAvailableRoomsMatchingRoomTypeFromRegistration(String registrationIdArg) {

        try {

            // assert the current user is a receptionist
            securityOps.assertUserIsReceptionist();

            // validate registration ID
            RegistrationId registrationId = RegistrationId.of(registrationIdArg);

            // load registration
            Registration registration = persistenceOps.obtainRegistration(registrationId);

            // load room type of the registration
            RoomType roomType = persistenceOps.obtainRoomType(registration.getRoomTypeId());

            // find available rooms matching the room type of the reservation
            Set<Room> availableRooms = persistenceOps.findAvailableRooms(roomType, registration.getStartDate(),
                    registration.getEndDate());
            if (availableRooms.isEmpty()) {
                /*
                    POINT OF INTEREST
                    -----------------
                    Present error if there are no available rooms matching the
                    room type of the reservation. The receptionist will have
                    to deal with this case by possibly proposing a room of
                    similar type.
                 */
                presenter.presentErrorIfNoAvailableRoomsMatchingRoomTypeAtCheckIn(registration, roomType);
                return;
            }

            // present available rooms for the receptionist to choose from
            presenter.presentAvailableRoomsForAssignment(registrationId, availableRooms);

        } catch (Exception e) {
            presenter.presentError(e);
        }

    }

    @Override
    public void receptionistAssignsRoom(String registrationIdArg, int roomNumberArg) {

    }
}
