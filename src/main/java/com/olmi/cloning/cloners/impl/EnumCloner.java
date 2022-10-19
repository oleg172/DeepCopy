package com.olmi.cloning.cloners.impl;

import com.olmi.cloning.cloners.Cloner;

import java.util.Map;

public class EnumCloner implements Cloner {

    @Override
    public boolean fit(Object object) {
        return object instanceof Enum;
    }

    @Override
    public Object deepCopy(Object object, Map<Object, Object> clones) {
        return object;
    }
}
