package com.github.hotelclean.core;

/**
 * Error thrown by invalid domain object.
 */
public class InvalidDomainObjectError extends GenericHotelManagementError{
    public InvalidDomainObjectError(String message) {
        super(message);
    }
}
