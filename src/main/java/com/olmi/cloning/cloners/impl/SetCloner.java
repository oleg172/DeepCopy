package com.olmi.cloning.cloners.impl;

import com.olmi.cloning.base.ObjectCloner;
import com.olmi.cloning.cloners.Cloner;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SetCloner implements Cloner {

    private final ObjectCloner objectCloner;

    public SetCloner(ObjectCloner objectCloner) {
        this.objectCloner = objectCloner;
    }

    @Override
    public boolean fit(Object object) {
        return object instanceof Set;
    }

    @Override
    public Object deepCopy(Object object, Map<Object, Object> clones) {
        Set objectSet = (Set) object;
        Set result = new HashSet();
        for (Object o : objectSet) {
            result.add(objectCloner.cloneObject(o, clones));
        }
        return result;
    }
}
