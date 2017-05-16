package com.anywareinteractive.seriousscientist.controller;

import com.anywareinteractive.seriousscientist.people.Scientist;

import java.util.ArrayList;

/**
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

    //=======
    // Scientists
    //=======
    public float scientistCost = 10000f;
    public float scientistPopulationRequirement = 20000f;
    public int scientistTrainingTime = 5; // In minutes

    public ArrayList<Scientist> scientists = new ArrayList<Scientist>();



    //*********************
    // Controller Constructor
    //*********************

    public GameController(String scientistName){
        Scientist scientist = new Scientist(scientistName);
        scientists.add(scientist);
    }

    //**********************
    // Controller Methods
    //**********************

    /**
     * This method purchases a new scientist
     * @return
     */
    public void purchaseScientist(){
        // Make sure the player has met the requirements
        if(money < scientistCost){
            System.out.println("You do not have enough money to purchase the scientist.");
            System.out.println(String.format("You need %s more money", scientistCost - money));
        }
        else if(seriousPopulation < scientistPopulationRequirement){
            System.out.println("You do not have the required serious population to purchase the scientist.");
            System.out.println(String.format("You need %s more people", scientistPopulationRequirement - seriousPopulation));
        }
        // They've got it! Let them purchase one.
        else {
            money -= scientistCost;
            //TODO Create a new thread here and make it wait until the time is complete for training.
            TrainingTimer trainingTimer = new TrainingTimer("Training Timer 1", this.scientistTrainingTime);
            trainingTimer.run();
            try {
                while(trainingTimer.isAlive()){
                    System.out.println("Waiting for thread to finish...");
                    Thread.sleep(1000);
                }
            }
            catch(InterruptedException e){
                System.out.println("Main thread was interrupted.");
            }

            Scientist scientist = new Scientist("Bob");
            scientists.add(scientist);
            // Absolutely no reason for these numbers. Just picked them.
            scientistCost *= 3.14;
            scientistPopulationRequirement *= 2.3;
            scientistTrainingTime = (scientists.size() * scientistTrainingTime * 2);
        }
    }
}

/**
 * This is a class for creating a training timer for new scientists.
 */
class TrainingTimer extends Thread {

    int executionTime;
    String threadName;

    TrainingTimer(String name, int minutes) {
        this.executionTime = minutes;
        this.threadName = name;
        System.out.println("Created a new thread " + this.threadName);
    }

    public void run() {
        try {
            for (int i = this.executionTime - 1; i > -1; i--) {
                for(int j = 60; j > 0; j--){
                    Thread.sleep(1000);
                    System.out.println((i) + ":" + j);
                }
                System.out.println("Time remaining: " + (i));
            }
        }
        catch(InterruptedException e){
            System.out.println("Warning! " + this.threadName + " was interrupted!");
        }

        System.out.println(this.threadName + " has stopped.");
    }
}

/**
 * To test functionality of the game thus far.
 */
class Test {
    public static void main(String[] args) {
        GameController gameController = new GameController("Tim");
        System.out.println("Current scientists: " + gameController.scientists.size());
        gameController.purchaseScientist();
        gameController.money += 1000000.00f;
        gameController.seriousPopulation += 10000000.00f;
        gameController.purchaseScientist();
    }
}

