package com.github.hotelclean.core.model.reservation;


import com.github.hotelclean.core.Validator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
public class ReservationId {

    @Getter(AccessLevel.NONE)
    String id;

    public static ReservationId of(final String id) {
        return new ReservationId(id);
    }

    @Builder
    public ReservationId(String id) {
        this.id = Validator.notBlank(id);
    }

    public String asString() {
        return id;
    }
}
