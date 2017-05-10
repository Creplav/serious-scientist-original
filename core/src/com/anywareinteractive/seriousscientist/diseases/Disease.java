package com.anywareinteractive.seriousscientist.diseases;

/**
 * Created by ben on 5/10/17.
 *
 * This is the base class of diseases.
 * It allows for diseases to be given names, descriptions, and effects.
 */
public abstract class Disease {
    //*****************************
    // DISEASE VARIABLES
    //*****************************

    /**
     * Name of the disease
     */
    protected String name = "No name given";
    /**
     * Description of the disease
     */
    protected String description = "No Description Available";
    /**
     * Defines whether or no the person infected with this disease will drop the cure.
     */
    protected boolean dropsCure = false;
    /**
     * Defines drop chance. Values from 0 - 1
     */
    protected float dropChance = 0;
    /**
     * Defines whether or not the person infected with this disease will annoy other people in the area
     */
    protected boolean reducesPatience = false;

    /**
     * Defines the amount at which the patience of people within the area will be reduced. Values from 0 - 1
     */
    protected float patienceReduction = 0;
    /**
     * Defines the radius of effect that patience reduction may have.
     */
    protected float patienceReductionRadius = 0;
    /**
     * Area around the person that will be effected by a patience reduction
     */
    protected final float patienceArea = (2.0f * 3.1415f * patienceReductionRadius);
    /**
     * Defines whether or not the disease is infectious.
     */
    protected boolean infectious = false;
    /**
     * Defines the total possible number of people that can be infected
     */
    protected int infectionNumber = 0;
    /**
     * Defines how likely the total possible number of people will be infected.
     * Values from 0 - 1
     */
    protected float infectionChance = 0;
    /**
     * Defines whether or not the disease is resistant to a cure
     */
    protected boolean resistant = false;
    /**
     * Defines the resistance level.
     * Values from 0 - 1
     */
    protected float resitance = 0;
}
