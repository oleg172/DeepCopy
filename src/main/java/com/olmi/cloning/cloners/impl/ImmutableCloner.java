package com.olmi.cloning.cloners.impl;

import com.olmi.cloning.cloners.Cloner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ImmutableCloner implements Cloner {

    private final List<Class> immutable = Arrays.asList(Character.class, Byte.class, Short.class, Integer.class,
            Long.class, Float.class, Double.class, Boolean.class, String.class);

    @Override
    public boolean fit(Object object) {
        return object != null && immutable.contains(object.getClass());
    }

    @Override
    public Object deepCopy(Object object, Map<Object, Object> clones) {
        return object;
    }
}
