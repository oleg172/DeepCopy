package com.olmi.cloning.cloners;

import java.util.Map;

public interface Cloner {

    boolean fit(Object object);

    Object deepCopy(Object object, Map<Object, Object> clones);
}
