package com.atm.statePattern.abstraction;


import com.atm.statePattern.implementation.states.ATM;

public abstract class ATMState {

    protected ATM atm;

    public abstract void handleState();


}
