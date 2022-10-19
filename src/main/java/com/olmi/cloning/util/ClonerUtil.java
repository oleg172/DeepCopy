package com.olmi.cloning.util;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;

public class ClonerUtil {

    private static Objenesis objenesis = new ObjenesisStd();

    private ClonerUtil() {

    }

    public static <T> T createInstance(Class<?> clazz) {
        ObjectInstantiator thingyInstantiator = objenesis.getInstantiatorOf(clazz);
        return (T) thingyInstantiator.newInstance();
    }
}
