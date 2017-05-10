package com.anywareinteractive.seriousscientist.people;

/**
 * Created by ben on 5/10/17.
 * This class is for creating new scientists and upgrading them.
 */
public class Scientist extends Person {
    float efficency = 0.2f;

    public Scientist(String name) {
        this.name = name;
        this.disease = null;
        this.patience = 100;
    }

    public float upgradeEfficency(){
        if(this.efficency != 1.0f){
            this.efficency += 0.1f;
            return this.efficency;
        }
        else {
            System.out.println("Scientist is at max efficiency");
            return this.efficency;
        }
    }
}

