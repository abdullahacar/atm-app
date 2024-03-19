package com.atm.statePattern.implementation.states;


import com.atm.statePattern.abstraction.ATMState;

import java.util.Objects;

public class ErrorState extends ATMState {

    private String message;

    public ErrorState(ATM atm) {
        this.atm = atm;
    }

    public ErrorState(ATM atm, String message) {
        this.atm = atm;
        this.message = message;
    }

    @Override
    public void handleState() {

        System.out.println(Objects.requireNonNullElse(message, "Sorry ! An error occurred !"));

        this.atm.setState(new InitialState(this.atm));
    }
}
