package com.anywareinteractive.seriousscientist.controller;

import com.anywareinteractive.seriousscientist.people.Scientist;
import com.badlogic.gdx.Game;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

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
    public float worldPopulation = 7500000000f; //Using current world population. (rounded down for simplicity)
    public float sickPopulation = 0f;
    public float seriousPopulation = 0f;

    //========
    // Money
    //=======
    /*
     * Would use an integer here. However, integers can only go up to 2,147,483,647.
     * Money can go a lot higher than that so we will use a long.
     * At first I decided on a float. But then I found out that there is an issue with the IEEE standard for
     * floats and handling money.
     * Starting off with $1000
     */
    public long money = 1000;

    //=======
    // Scientists
    //=======
    public long scientistCost = 10000;
    public float scientistPopulationRequirement = 20000f;
    /*
     * Using a short because it goes to 32,767 and that would equal about 45 days which is plenty of time.
     * The short data type is only 2 bytes compared to the four of an integer.
     */
    //TODO Decide on a better way to handle time
    public short scientistTrainingTime = 5; // In minutes

    public ArrayList<Scientist> scientists = new ArrayList<Scientist>();

    public Scientist mainScientist = null;


    //*********************
    // Controller Constructor
    //*********************

    public GameController(String scientistName){
        Scientist scientist = new Scientist(scientistName, this);
        mainScientist = scientist;
        scientists.add(scientist);
    }

    //**********************
    // Controller Methods
    //**********************

    /**
     * This method purchases a new scientist
     * @return
     */
    public void purchaseScientist(final GameController gameController) {
        // Make sure the player has met the requirements
        if (money < scientistCost) {
            System.out.println("You do not have enough money to purchase the scientist.");
            System.out.println(String.format("You need %s more money", scientistCost - money));
        } else if (seriousPopulation < scientistPopulationRequirement) {
            System.out.println("You do not have the required serious population to purchase the scientist.");
            System.out.println(String.format("You need %s more people", scientistPopulationRequirement - seriousPopulation));
        }
        // They've got it! Let them purchase one.
        else {
            money -= scientistCost;
            final Thread trainingThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("Training Scientist...");
                        /*
                         * Setting the sleep time to 5 seconds
                         */
                        final long sleepTime = 5000;
                        //Creating a new thread to use as a count down timer
                        final Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    //Create a time remaining so that we can view how many seconds are left
                                    long timeRemaining = sleepTime / 1000;
                                    //Print out the initial remaining time
                                    System.out.println("Time Remaining: " + timeRemaining);
                                    //Print out the remaining time every second until it reaches 0
                                    while (timeRemaining != 0) {
                                        Thread.sleep(1000);
                                        timeRemaining--;
                                        System.out.println("Time Remaining: " + timeRemaining);
                                    }
                                    //Notify that the thread has finished and interrupt the thread to exit it.
                                    System.out.println("Thread finished.");
                                    throw new InterruptedException();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        //Starting the timer thread
                        thread.start();
                        //Sleeping the current thread until the time is up.
                        Thread.sleep(sleepTime);
                        //Report that the thread has finished
                        System.out.println("DONE!");
                        System.out.println("Thread Finished");
                        //Throw an interruption to stop the thread
                        throw new InterruptedException();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            //Start the training thread
            trainingThread.start();
            //Yield until the training thread has stopped.
            while(trainingThread.isAlive()){
                Thread.yield();
            }
            //Create a new scanner for input
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter a name for this scientist: ");
            final String scientistName = scanner.next();
            //Create a new scientist and add it to the list of current scientists.
            Scientist scientist = new Scientist(scientistName, gameController);
            scientists.add(scientist);
            // Absolutely no reason for these numbers. Just picked them.
            scientistCost *= 3.14;
            scientistPopulationRequirement *= 2.3;
            scientistTrainingTime = (short) (scientists.size() * scientistTrainingTime * 2);
        }
    }
}

/**
 * This is a class for creating a training timer for new scientists.
 * (MAYBE OBSOLETE NOW)
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
class GameTests {
    public static void main(String[] args) {
        if (scientistQuantityTest()) System.out.println("Scientist Quantity Test Passed");
        else System.out.println("Scientist Quantity Test passed");
    }

    public static boolean scientistQuantityTest() {
        GameController gameController = new GameController("Tim");
        System.out.println("Current scientists: " + gameController.scientists.size());
        gameController.purchaseScientist(gameController);
        gameController.money += 1000000.00f;
        gameController.seriousPopulation += 10000000.00f;
        gameController.purchaseScientist(gameController);
        System.out.println("Current Scientists: " + gameController.scientists.size());
        if (gameController.scientists.size() == 2) return true;
        else return false;
    }

    public static boolean researchTest() {
        GameController gameController = new GameController("Mark");
        return false;
    }
}
