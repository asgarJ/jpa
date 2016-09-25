package com.javacodegeeks.ultimate.entities;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by asgar on 9/25/16.
 */

@Converter
public class BooleanConverter implements AttributeConverter<Boolean, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Boolean attribute) {
        if (Boolean.TRUE.equals(attribute)) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public Boolean convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return Boolean.FALSE;
        if (dbData == 1)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }
}
