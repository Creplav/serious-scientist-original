package com.anywareinteractive.seriousscientist.people;

import com.anywareinteractive.seriousscientist.diseases.Disease;

/**
 * Created by ben on 5/10/17.
 *
 * This is the base class for people.
 * It allows people to get sick and be given a name and disease.
 */
public abstract class Person implements ISickable {
    protected String name = "No name";
    /**
     * The patience the person currently has.
     */
    protected int patience = 100;
    protected Disease disease = null;

    @Override
    public Disease getSick() {
        return null;
    }
}
