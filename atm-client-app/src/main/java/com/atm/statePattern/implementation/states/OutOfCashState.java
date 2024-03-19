package com.atm.statePattern.implementation.states;

import com.atm.statePattern.abstraction.ATMState;

public class OutOfCashState extends ATMState {

    public OutOfCashState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void handleState() {
        System.out.println("Sorry ! ATM is out of cash !");
        this.atm.setState(new DisplayTheCommandsState(this.atm));
    }
}
