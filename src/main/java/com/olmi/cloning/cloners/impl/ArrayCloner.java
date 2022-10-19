package com.olmi.cloning.cloners.impl;

import com.olmi.cloning.base.ObjectCloner;
import com.olmi.cloning.cloners.Cloner;

import java.lang.reflect.Array;
import java.util.Map;

public class ArrayCloner implements Cloner {

    private final ObjectCloner objectCloner;

    public ArrayCloner(ObjectCloner objectCloner) {
        this.objectCloner = objectCloner;
    }

    @Override
    public boolean fit(Object object) {
        return object != null && object.getClass().isArray();
    }

    @Override
    public Object deepCopy(Object object, Map<Object, Object> clones) {
        int length = Array.getLength(object);
        Object result = Array.newInstance(object.getClass().getComponentType(), length);

        if (object.getClass().getComponentType().isPrimitive()) {
            System.arraycopy(object, 0, result, 0, length);
        } else {
            for (int i = 0; i < length; i++) {
                Array.set(result, i, objectCloner.cloneObject(Array.get(object, i), clones));
            }
        }
        return result;
    }
}
