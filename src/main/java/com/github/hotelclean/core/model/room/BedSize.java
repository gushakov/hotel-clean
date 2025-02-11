package com.github.hotelclean.core.model.room;

import com.github.hotelclean.core.Validator;
import lombok.experimental.FieldDefaults;

/**
 * Bed sizes commonly used in hotel management domain. Each bed size
 * corresponds to a specific number of persons who can occupy it at
 * a given time.
 */
@FieldDefaults(makeFinal = true)
public enum BedSize {

    Single("Single", 1),
    Twin("Twin", 1),
    Double("Double", 2),
    Queen("Queen", 2),
    King("King", 2),
    ;

    String size;
    int persons;

    BedSize(String size, int persons) {
        this.size = Validator.notBlank(size);
        this.persons = Validator.strictlyPositive(persons);
    }
}
