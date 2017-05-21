package com.anywareinteractive.seriousscientist.exceptions;

/**
 * Created by ben on 5/21/17.
 */
public class InsufficentFundsException extends Exception{

    private long amount;

    public InsufficentFundsException(long amount){
        this.amount = amount;
    }

    public long getAmount(){
        return this.amount;
    }
}
