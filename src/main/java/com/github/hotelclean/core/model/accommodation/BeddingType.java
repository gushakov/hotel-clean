package com.github.hotelclean.core.model.accommodation;

import com.github.hotelclean.core.Validator;

import java.math.BigDecimal;

/**
 * Type of bedding provided by a particular accommodation.
 */
public enum BeddingType {

    Single(BigDecimal.ONE),
    Twin(BigDecimal.valueOf(1.1d)),
    Double(BigDecimal.valueOf(1.2d)),
    Queen(BigDecimal.valueOf(1.3d)),
    King(BigDecimal.valueOf(1.4d)),
    Triple(BigDecimal.valueOf(1.5d)),
    Family(BigDecimal.valueOf(1.6d));

    final BigDecimal priceMultiplier;

    BeddingType(BigDecimal priceMultiplier) {
        this.priceMultiplier = Validator.notNull(priceMultiplier);
    }
}
