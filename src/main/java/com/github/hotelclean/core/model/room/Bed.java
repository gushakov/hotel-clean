package com.github.hotelclean.core.model.room;

import com.github.hotelclean.core.Validator;
import lombok.Value;

/**
 * Model of a bed used to furnish a room in the hotel.
 */
@Value
public class Bed {
    BedSize size;
    String manufacturer;

    public static Bed of(final BedSize size, final String manufacturer) {
        return new Bed(size, manufacturer);
    }

    public Bed(BedSize size, String manufacturer) {
        this.size = Validator.notNull(size);
        this.manufacturer = Validator.notBlank(manufacturer);
    }
}
