package com.github.hotelclean.core.model.accommodation;

import com.github.hotelclean.core.Validator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Aggregate root. Describes a type or a category of rooms available
 * at the hotel.
 */
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RoomType {

    /**
     * Unique ID of this room type.
     */
    RoomTypeId id;

    /**
     * Short name of this room type: i.e. "Double Deluxe with Ocean View"
     */
    String name;

    /**
     * Collection of bedding types available for this room type.
     */
    Collection<BeddingType> beddingTypes;

    /**
     * Maximum number of persons which can be accommodated by this
     * room type.
     */
    Occupancy occupancy;

    /**
     * Accommodation class associated with this room type.
     */
    AccommodationClass accommodationClass;

    /**
     * Base price for a night for this room type.
     */
    BigDecimal basePricePerNight;

    /**
     * Any other amenities included in this room type, i.e.: any view,
     * balcony, etc.
     */
    String amenities;

    @Builder
    public RoomType(RoomTypeId id, String name, Collection<BeddingType> beddingTypes, Occupancy occupancy,
                    AccommodationClass accommodationClass, BigDecimal basePricePerNight, String amenities) {
        this.id = Validator.notNull(id);
        this.name = Validator.notBlank(name);
        this.beddingTypes = Validator.notEmpty(beddingTypes);
        this.occupancy = Validator.notNull(occupancy);
        this.accommodationClass = Validator.notNull(accommodationClass);
        this.basePricePerNight = Validator.notNull(basePricePerNight);

        // can be null
        this.amenities = amenities;
    }
}
