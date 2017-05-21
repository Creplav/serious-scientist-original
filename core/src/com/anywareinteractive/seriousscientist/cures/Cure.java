package com.anywareinteractive.seriousscientist.cures;

/**
 * Created by Ben on 5/10/17.
 */
public abstract class Cure implements IBreakable {
    public long cost = 0; //Cost to produce a new cure
    public long price = 0; //Selling price
    public int creationTime = 0; //Amount of time it takes to produce this cure
    public int seriousnessInfection = 0;
    public long researchCost = 0;
    public int researchTime = 0; //Amount of time it take to research this cure
    public boolean unlocked = false; //Whether this cure has been researched or not
    public String description = "No Description";
    public String name = "No Name";

    @Override
    public void drop() {
        System.out.println(String.format("{} has dropped their cure. How clumsy! This will have no effect on serious or sick populations.", "Bob"));
        breakItem();
    }

    @Override
    public void breakItem() {

    }
}
