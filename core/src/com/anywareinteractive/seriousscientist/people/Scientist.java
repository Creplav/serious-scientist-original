package com.anywareinteractive.seriousscientist.people;

/**
 * This class is for creating new scientists and upgrading them.
 */
public class Scientist extends Person {
    float efficiency = 0.2f;

    public Scientist(String name) {
        this.name = name;
        this.disease = null;
        this.patience = 100;
    }

    /**
     * This method upgrades the efficiency of the scientist in order to purduce more cures.
     * @return
     */
    public float upgradeEfficiency(){
        if(this.efficiency != 1.0f){
            this.efficiency += 0.1f;
            return this.efficiency;
        }
        else {
            System.out.println("Scientist is at max efficiency");
            return this.efficiency;
        }
    }
}

