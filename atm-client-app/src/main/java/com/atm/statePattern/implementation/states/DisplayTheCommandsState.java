package com.atm.statePattern.implementation.states;

import com.atm.statePattern.abstraction.ATMState;
import com.atm.statePattern.implementation.enums.Command;

import java.util.Arrays;

public class DisplayTheCommandsState extends ATMState {

    public DisplayTheCommandsState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void handleState() {

        System.out.println("What do you want to do ? Please make a selection : ");

        Arrays.stream(Command.values()).sequential().forEach(command -> System.out.println(command.toString()));

        int i = this.atm.SCANNER.nextInt();

        Command command = Command.get(i);

        if (command == null) {
            this.atm.setState(this);
            return;
        }

        handleSelection(command);

    }


    void handleSelection(Command command) {
        switch (command) {
            case VIEW_BALANCE -> this.atm.setState(new ViewBalanceState(this.atm));
            case WITHDRAW -> this.atm.setState(new WithDrawState(this.atm));
            case DEPOSIT -> this.atm.setState(new DepositState(this.atm));
            case LOG_OUT -> this.atm.setState(new LogOutState(this.atm));
            case CHANGE_PIN -> this.atm.setState(new ChangePinState(this.atm));
            default -> this.atm.setState(this);
        }
    }

}
