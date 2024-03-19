package com.atm.statePattern.implementation.states;

import com.atm.statePattern.abstraction.ATMState;

public class LoggedInState extends ATMState {

    public LoggedInState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void handleState() {
        System.out.println("You're successfully logged in.");
        this.atm.setState(new DisplayTheCommandsState(this.atm));
    }
}
