package com.anywareinteractive.seriousscientist.controller;

import com.anywareinteractive.seriousscientist.people.Scientist;

/**
 * Created by ben on 5/13/17.
 *
 * This class is for a good portion of the game logic. It will hold all of the important stuff like money and population.
 */
public class GameController {

    //****************************
    // Controller variables
    //****************************

    //=======
    // Population
    //=======
    /*
     * Creating float values because they only use 4 bytes of memory compared to 8 for a double.
     * Also because of integer limits to 2.15 billion (approximate)
     */
    public float worldPopulation = 7500000000.00f; //Using current world population. (rounded down for simplicity)
    public float sickPopulation = 0.0f;
    public float seriousPopulation = 0.0f;

    //========
    // Money
    //=======
    /*
     * Would use an integer here. However, integers can only go up to 2,147,483,647.
     * Money can go a lot higher than that so we will use a float.
     * Starting off with $1000
     */
    public float money = 1000.0f;

    //=========
    // Current employees
    //=========

    // There is no need to go above 2.1 billion scientists and cashiers. Plus that would be insanely expensive. Let's use an integer.
    public int currentScientists = 1;
    public int currentCahsiers = 1; // Max 4 per store

    //=======
    // Scientists
    //=======
    public float scientistCost = 10000f;
    public float scientistPopulationRequirement = 20000f;
    //TODO Create a training time for scientists.

    //TODO Create a collection for scientists




    //*********************
    // Controller Constructor
    //*********************

    public GameController(String scientistName){
        Scientist scientist = new Scientist(scientistName);
    }

    //**********************
    // Controller Methods
    //**********************

    /**
     * This method purchases a new scientist
     * @return
     */
    public Scientist purchaseScientist(){
        // Make sure the player has met the requirements
        if(money > scientistCost){
            System.out.println("You do not have enough money to purchase the scientist.");
            System.out.println(String.format("You need %s more money", scientistCost - money));
            return null;
        }
        else if(seriousPopulation < scientistPopulationRequirement){
            System.out.println("You do not have the required serious population to purchase the scientist.");
            System.out.println(String.format("You need %s more people", scientistPopulationRequirement - seriousPopulation));
            return null;
        }
        // They've got it! Let them purchase one.
        else {
            money -= scientistCost;
            //TODO Create a new thread here and make it wait until the time is complete for training.
            Scientist scientist = new Scientist("Bob");
            currentScientists++;
            // Absolutely no reason for these numbers. Just picked them.
            scientistCost *= 3.14;
            scientistPopulationRequirement *= 2.3;
            return scientist;
        }
    }
}
