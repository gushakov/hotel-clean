package com.github.hotelclean.core.port.persistence;

import com.github.hotelclean.core.model.accommodation.RoomType;
import com.github.hotelclean.core.model.accommodation.RoomTypeId;
import com.github.hotelclean.core.model.reservation.Reservation;
import com.github.hotelclean.core.model.reservation.ReservationId;
import com.github.hotelclean.core.model.room.Room;
import com.github.hotelclean.core.model.room.RoomNumber;

import java.util.Set;

public interface PersistenceOperationsOutputPort {
    Reservation obtainReservation(ReservationId reservationId);

    RoomType obtainRoomType(RoomTypeId roomTypeId);

    Set<Room> obtainAllRooms();

    Room obtainRoom(RoomNumber roomNumber);

    void save(Room room);

    void save(Reservation reservation);
}
