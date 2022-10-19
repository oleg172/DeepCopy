package com.olmi.cloning;

import com.olmi.cloning.base.ObjectCloner;

import java.util.HashMap;

public class DeepCloner {

    private final ObjectCloner objectCloner;

    public DeepCloner() {
        objectCloner = new ObjectCloner();
    }

    public <T> T deepCopy(T object) {
        return objectCloner.cloneObject(object, new HashMap<>());
    }
}
