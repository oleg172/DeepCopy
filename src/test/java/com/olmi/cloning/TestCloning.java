package com.olmi.cloning;

import com.olmi.cloning.model.Person;
import com.olmi.cloning.util.ModelGeneratorUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class TestCloning {

    @Test
    public void testCloning() {
        DeepCloner deepCloner = new DeepCloner();
        Person person = ModelGeneratorUtil.getTestedPerson();
        Person copiedPerson = deepCloner.deepCopy(person);

        assertNotSame(person, copiedPerson);
        assertEquals(person, copiedPerson);
    }
}
