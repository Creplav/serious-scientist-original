package com.anywareinteractive.seriousscientist.controller;

/**
 * Created by ben on 5/13/17.
 */
public class GameController {

    //****************************
    // Controller variables
    //****************************

    /*
     * Creating float values because they only use 4 bytes of memory compared to 8 for a double.
     * Also because of integer limits to 2.15 billion (approximate)
     */
    public float worldPopulation = 7500000000.00f; //Using current world population. (rounded down for simplicity)
    public float sickPopulation = 0.0f;
    public float seriousPopulation = 0.0f;

    /*
     * Would use an integer here. However, integers can only go up to 2,147,483,647.
     * Money can go a lot higher than that so we will use a float.
     * Starting off with $1000
     */
    public float money = 1000.0f;

    // There is no need to go above 2.1 billion scientists and cashiers. Plus that would be insanely expensive. Let's use an integer.
    public int currentScientists = 1;
    public int currentCahsiers = 1; // Max 4 per store
}
