package com.anywareinteractive.seriousscientist.cures;

/**
 * Created by Ben on 5/10/17.
 */
public abstract class Cure implements IBreakable {
    protected int cost = 0;
    protected int price = 0;
    protected int seriousnessInfection = 0;
    protected int researchCost = 0;
    protected int researchTime = 0;
    protected String description = "No Description";
    protected String name = "No Name";

    public Cure createCure(Cure cure){
        //Set up a timer for cure creation.
        return cure;
    }

    @Override
    public void drop() {
        System.out.println(String.format("{} has dropped their cure. How clumsy! This will have no effect on serious or sick populations.", "Bob"));
        breakItem();
    }

    @Override
    public void breakItem() {

    }
}
