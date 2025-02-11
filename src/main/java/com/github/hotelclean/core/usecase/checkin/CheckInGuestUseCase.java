package com.github.hotelclean.core.usecase.checkin;

import com.github.hotelclean.core.model.DefaultRoomTypeMatchingSpecification;
import com.github.hotelclean.core.model.RoomTypeMatchingSpecification;
import com.github.hotelclean.core.model.accommodation.RoomType;
import com.github.hotelclean.core.model.registration.Registration;
import com.github.hotelclean.core.model.registration.RegistrationId;
import com.github.hotelclean.core.model.room.Room;
import com.github.hotelclean.core.model.room.RoomNumber;
import com.github.hotelclean.core.port.persistence.PersistenceOperationsOutputPort;
import com.github.hotelclean.core.port.securirty.SecurityOperationsOutputPort;
import com.github.hotelclean.core.port.transaction.TransactionOperationsOutputPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.stream.Collectors;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CheckInGuestUseCase implements CheckInGuestInputPort {

    CheckInGuestPresenterOutputPort presenter;
    SecurityOperationsOutputPort securityOps;
    PersistenceOperationsOutputPort persistenceOps;
    TransactionOperationsOutputPort txOps;

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

            // instantiate room matching strategy
            RoomTypeMatchingSpecification roomTypeMatchingSpecification = new DefaultRoomTypeMatchingSpecification(roomType);

            /*
                POINT OF INTEREST
                -----------------
                For (the current) room assignment use case we do not check
                the dates of the reservation. The dates would have been
                verified by a different use case during the issue the
                reservation.
             */

            // get all rooms and filter only available and matching ones
            Set<Room> availableMatchingRooms = persistenceOps.obtainAllRooms().stream()
                    .filter(room -> roomIsAvailableAndMatchesRoomType(roomTypeMatchingSpecification, room))
                    .collect(Collectors.toSet());
            if (availableMatchingRooms.isEmpty()) {
                /*
                    POINT OF INTEREST
                    -----------------
                    Present error if there are no available rooms matching the
                    room type of the reservation. This may happen in the case
                    of overbooking. The receptionist will have to deal with this
                    case by possibly proposing a room of similar type.
                 */
                presenter.presentErrorIfNoAvailableRoomsMatchingRoomTypeDuringCheckIn(registration, roomType);
                return;
            }

            // present available rooms for the receptionist to choose from
            presenter.presentAvailableMatchingRoomsForAssignment(registrationId, availableMatchingRooms);

        } catch (Exception e) {
            presenter.presentError(e);
        }

    }

    @Override
    public void receptionistAssignsRoom(String registrationIdArg, int roomNumberArg) {
        try {

            // assert the current user is a receptionist
            securityOps.assertUserIsReceptionist();

            // validate registration ID and room number
            RegistrationId registrationId = RegistrationId.of(registrationIdArg);
            RoomNumber roomNumber = RoomNumber.of(roomNumberArg);

            // load registration
            Registration registration = persistenceOps.obtainRegistration(registrationId);

            // load room type of the registration
            RoomType roomType = persistenceOps.obtainRoomType(registration.getRoomTypeId());

            // load room
            Room room = persistenceOps.obtainRoom(roomNumber);

            // instantiate room matching strategy
            RoomTypeMatchingSpecification roomTypeMatchingSpecification = new DefaultRoomTypeMatchingSpecification(roomType);

            // verify that room is (still) available and matches the room type
            if (!roomIsAvailableAndMatchesRoomType(roomTypeMatchingSpecification, room)) {
                presenter.presentErrorOnRoomTypeMismatchDuringAssignment(registration, roomType,
                        room);
                return;
            }

            // mark the room as occupied and assign the room to the registration

            Room occupiedRoom = room.occupy();
            Registration registrationWithRoomAssigned = registration.assignRoom(roomNumber);

            /*
                POINT OF INTEREST
                -----------------

                We must persist the changes to two aggregates in a transaction.
             */
            txOps.doInTransaction(false, () -> {
                persistenceOps.save(occupiedRoom);
                persistenceOps.save(registrationWithRoomAssigned);
            });

            // present result of the use case upon a successful commit
            txOps.doAfterCommit(() -> presenter.presentResultOfSuccessfulRoomAssignment(registrationWithRoomAssigned));

        } catch (Exception e) {
            presenter.presentError(e);
        }

    }

    /**
     * Returns {@code true} if the given {@code room} is available for guests,
     * is not already occupied, and matches the provided room type matching specification.
     *
     * @param roomTypeMatchingSpecification specification matching a room to a room type
     * @param room                          a hotel room
     * @return {@code true} if room is available, not occupied, and matches the specification,
     * returns {@code false} otherwise
     */
    private boolean roomIsAvailableAndMatchesRoomType(RoomTypeMatchingSpecification roomTypeMatchingSpecification,
                                                      Room room) {
        return room.isAvailableForGuests()
                && !room.isOccupied()
                && roomTypeMatchingSpecification.roomTypeMatches(room);
    }

}
