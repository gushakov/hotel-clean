package com.github.hotelclean.core.model.registration;


import com.github.hotelclean.core.Validator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
public class RegistrationId {

    @Getter(AccessLevel.NONE)
    String id;

    public static RegistrationId of(final String id) {
        return new RegistrationId(id);
    }

    @Builder
    public RegistrationId(String id) {
        this.id = Validator.notBlank(id);
    }

    public String asString() {
        return id;
    }
}
