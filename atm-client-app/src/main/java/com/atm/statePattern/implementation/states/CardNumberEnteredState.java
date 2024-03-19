package com.atm.statePattern.implementation.states;

import com.atm.dto.LoginCredential;
import com.atm.statePattern.abstraction.ATMState;

import java.util.InputMismatchException;

public class CardNumberEnteredState extends ATMState {

    private final String cardNumber;

    public CardNumberEnteredState(ATM atm, String cardNumber) {
        this.atm = atm;
        this.cardNumber = cardNumber;
    }

    @Override
    public void handleState() {
        try {
            System.out.println("Please enter your PIN (4 digits)");
            int pin = this.atm.getSCANNER().nextInt();

            if (String.valueOf(pin).length() != 4) {
                System.out.println("Your PIN must be only 4 digits !");
                this.atm.setState(this);
                return;
            }
            this.atm.setState(new LoggingInState(this.atm, LoginCredential.builder().cardNumber(cardNumber).pin(pin).build()));
        } catch (InputMismatchException e) {
            System.out.println("Please use only numeric values !");
            this.atm.SCANNER.nextLine();
            this.atm.setState(this);
        } catch (Exception e) {
            this.atm.setState(new ErrorState(this.atm, e.getMessage()));
        }
    }
}
