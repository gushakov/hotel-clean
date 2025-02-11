package com.github.hotelclean.core.model.registration;

import com.github.hotelclean.core.GenericHotelManagementError;

/**
 * Error thrown when dates for registration are invalid.
 */
public class IllegalRegistrationDatesError extends GenericHotelManagementError {
    public IllegalRegistrationDatesError(String message) {
    }
}
