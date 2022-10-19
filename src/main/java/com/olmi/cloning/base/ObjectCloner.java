package com.olmi.cloning.base;

import com.olmi.cloning.cloners.Cloner;
import com.olmi.cloning.cloners.impl.*;
import com.olmi.cloning.util.ClonerUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ObjectCloner {

    private final List<Cloner> cloners;
    private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog("ObjectCloner");

    public ObjectCloner() {
        this.cloners = new ArrayList<>();
        this.cloners.add(new ImmutableCloner());
        this.cloners.add(new ArrayListCloner(this));
        this.cloners.add(new MapCloner(this));
        this.cloners.add(new EnumCloner());
        this.cloners.add(new ArrayCloner(this));
        this.cloners.add(new SetCloner(this));
        this.cloners.add(new NullCloner());
    }

    public <T> T cloneObject(T object, Map<Object, Object> clones) {
        try {
            if (object == null) {
                return null;
            }
            if (clones != null) {
                T cloneObject = (T) clones.get(object);
                if (cloneObject != null) {
                    return cloneObject;
                }
            }
            Class<?> clazz = object.getClass();
            Optional<Cloner> cloner = getCloner(object);
            T cloneObject;
            if (cloner.isPresent()) {
                cloneObject = (T) cloner.get().deepCopy(object, clones);
                clones.put(object, cloneObject);
            } else {
                cloneObject = ClonerUtil.createInstance(object.getClass());
                clones.put(object, cloneObject);
                Field[] fields = object.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Optional<Cloner> fieldCloner = getCloner(field.get(object));
                    if (fieldCloner.isPresent()) {
                        field.set(cloneObject, fieldCloner.get().deepCopy(field.get(object), clones));
                    } else {
                        field.set(cloneObject, cloneObject(field.get(object), clones));
                    }
                }
            }
            while (true) {
                if (Object.class.equals(clazz)) {
                    break;
                }
                clazz = clazz.getSuperclass();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Optional<Cloner> fieldCloner = getCloner(field.get(object));
                    if (fieldCloner.isPresent()) {
                        field.set(cloneObject, fieldCloner.get().deepCopy(field.get(object), clones));
                    } else {
                        field.set(cloneObject, cloneObject(field.get(object), clones));
                    }
                }
            }
            return cloneObject;
        } catch (IllegalAccessException e) {
            log.error(String.format("Can't copy object '%s'", object.getClass().getName()), e);
        }
        return null;
    }

    private Optional<Cloner> getCloner(Object object) {
        return cloners.stream()
                .filter(c -> c.fit(object))
                .findFirst();
    }
}
