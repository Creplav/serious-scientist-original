package com.anywareinteractive.seriousscientist.people;

import com.anywareinteractive.seriousscientist.cures.*;
import com.anywareinteractive.seriousscientist.controller.*;
import com.anywareinteractive.seriousscientist.exceptions.InsufficentFundsException;

/**
 * This class is for creating new scientists and upgrading them.
 */
public class Scientist extends Person {
    int efficiency = 1;
    GameController gameController;
    public Scientist(String name, GameController controller) {
        this.name = name;
        this.disease = null;
        this.patience = 100;
        this.gameController = controller;
    }

    /**
     * This method upgrades the efficiency of the scientist in order to purduce more cures.
     * @return
     */
    public int upgradeEfficiency(){
        if(this.efficiency != 5){
            this.efficiency += 1;
            return this.efficiency;
        }
        else {
            System.out.println("Scientist is at max efficiency");
            return this.efficiency;
        }
    }

    public void researchCure(Cure cure) throws InsufficentFundsException {
        if(gameController.money >= cure.researchCost){
            gameController.money -= cure.researchCost;
            // Create a new thread here for counting down time.

            cure.unlocked = true;
        }
        else {
            throw new InsufficentFundsException(cure.researchCost - gameController.money);
        }
    }

    public void createCure(Cure cure) throws InsufficentFundsException {
        if(gameController.money >= cure.cost){
            gameController.money -= cure.cost;
            // Create a new thread here for counting down time.

            //Add cure to list of cures.
        }
        else {
            throw new InsufficentFundsException(cure.cost - gameController.money);
        }
    }
}

