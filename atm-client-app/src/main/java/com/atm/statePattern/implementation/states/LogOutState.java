package com.atm.statePattern.implementation.states;

import com.atm.statePattern.abstraction.ATMState;

public class LogOutState extends ATMState {

    public LogOutState(ATM atm) {
        this.atm = atm;
    }


    @Override
    public void handleState() {
        this.atm.setLoggedIn(false);
        System.out.println("You are successfully logged out. Have a nice day !");
        this.atm.setState(new InitialState(this.atm));
    }
}
