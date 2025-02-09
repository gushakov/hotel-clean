package com.github.hotelclean.core;

/**
 * Top-level generic error thrown by our application.
 */
public class GenericHotelManagementError extends RuntimeException {
    public GenericHotelManagementError() {
    }

    public GenericHotelManagementError(String message) {
        super(message);
    }
}
