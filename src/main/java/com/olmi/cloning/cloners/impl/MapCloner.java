package com.olmi.cloning.cloners.impl;

import com.olmi.cloning.base.ObjectCloner;
import com.olmi.cloning.cloners.Cloner;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapCloner implements Cloner {

    private final ObjectCloner objectCloner;

    public MapCloner(ObjectCloner objectCloner) {
        this.objectCloner = objectCloner;
    }

    @Override
    public boolean fit(Object object) {
        return object instanceof Map;
    }

    @Override
    public Object deepCopy(Object object, Map<Object, Object> clones) {
        Map<Object, Object> map = (Map) object;

        Map result;
        if (object instanceof HashMap) {
            result = new HashMap();
        } else {
            result = new LinkedHashMap();
        }
        for (Map.Entry entry : map.entrySet()) {
            result.put(objectCloner.cloneObject(entry.getKey(), clones), objectCloner.cloneObject(entry.getValue(), clones));
        }
        return result;
    }
}
