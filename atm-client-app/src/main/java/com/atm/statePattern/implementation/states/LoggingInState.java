package com.atm.statePattern.implementation.states;

import com.atm.dto.CardDTO;
import com.atm.dto.CardQueryModel;
import com.atm.dto.response.ApiResponse;
import com.atm.dto.LoginCredential;
import com.atm.statePattern.abstraction.ATMState;

public class LoggingInState extends ATMState {

    private final LoginCredential credential;

    public LoggingInState(ATM atm, LoginCredential credential) {
        this.atm = atm;
        this.credential = credential;
    }

    @Override
    public void handleState() {

        if (atm.isLoggedIn()) {
            System.out.println("You are already logged in.");
            this.atm.setState(new LoggedInState(this.atm));
        } else {
            System.out.println("Please wait...");

            ApiResponse<CardDTO> cardDTOApiResponse = this.atm.getAccountService().validatePin(

                    CardQueryModel.builder().cardNumber(credential.getCardNumber()).pin(credential.getPin()

                    ).build());

            if (cardDTOApiResponse.isSuccessful()) {
                this.atm.setLoggedIn(true);
                this.atm.setCredential(credential);
                this.atm.setState(new LoggedInState(this.atm));
            } else {
                System.out.println("Log In operation failed !");
                this.atm.setState(new InitialState(this.atm));
            }
        }
    }
}
