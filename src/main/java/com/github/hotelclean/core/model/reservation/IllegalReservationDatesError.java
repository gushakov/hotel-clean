package com.github.hotelclean.core.model.reservation;

import com.github.hotelclean.core.GenericHotelManagementError;

/**
 * Error thrown when dates for reservation are invalid.
 */
public class IllegalReservationDatesError extends GenericHotelManagementError {
    public IllegalReservationDatesError(String message) {
    }
}
