package com.github.hotelclean.core.model.accommodation;

import com.github.hotelclean.core.Validator;

import java.math.BigDecimal;

/**
 * Class of accommodation. Each accommodation class is
 * associated with different price multiplier which will
 * be used together with the base price of a room type
 * to calculate the total price of a reservation.
 */
public enum AccommodationClass {

    Standard(BigDecimal.ONE),
    Deluxe(BigDecimal.valueOf(1.5d)),
    Suite(BigDecimal.valueOf(2.0d)),
    ExecutiveSuite(BigDecimal.valueOf(2.5d)),
    PresidentialSuite(BigDecimal.valueOf(3.0d)),
    ;

    final BigDecimal priceMultiplier;

    AccommodationClass(BigDecimal priceMultiplier) {
        this.priceMultiplier = Validator.notNull(priceMultiplier);
    }
}
