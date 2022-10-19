package com.olmi.cloning;

import com.olmi.cloning.util.ModelGeneratorUtil;

public class Main {

    public static void main(String[] args) {
        DeepCloner deepCloner = new DeepCloner();
        deepCloner.deepCopy(ModelGeneratorUtil.getTestedPerson());
    }

}
