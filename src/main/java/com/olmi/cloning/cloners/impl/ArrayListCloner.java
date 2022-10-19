package com.olmi.cloning.cloners.impl;

import com.olmi.cloning.base.ObjectCloner;
import com.olmi.cloning.cloners.Cloner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ArrayListCloner implements Cloner {

    private final ObjectCloner objectCloner;

    public ArrayListCloner(ObjectCloner objectCloner) {
        this.objectCloner = objectCloner;
    }

    @Override
    public boolean fit(Object object) {
        return object instanceof List<?>;
    }

    @Override
    public Object deepCopy(Object object, Map<Object, Object> clones) {
        if (object == null) {
            return null;
        }
        List list = (List) object;
        List result;
        if (object instanceof ArrayList) {
            result = new ArrayList();
        } else {
            result = new LinkedList();
        }
        for (int i = 0; i < list.size(); i++) {
            result.add(objectCloner.cloneObject(list.get(i), clones));
        }
        return result;
    }
}
