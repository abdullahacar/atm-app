package com.atm.statePattern.implementation.states;

import com.atm.dto.CardDTO;
import com.atm.dto.CardQueryModel;
import com.atm.dto.response.ApiResponse;
import com.atm.statePattern.abstraction.ATMState;

public class ChangePinState extends ATMState {

    public ChangePinState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void handleState() {

        System.out.println("Please enter your new PIN : ");

        int newPin = this.atm.getSCANNER().nextInt();

        if (String.valueOf(newPin).length() != 4) {
            System.out.println("Your PIN must be 4 digits !");
            this.atm.setState(this);
            return;
        }

        ApiResponse<CardDTO> cardDTOApiResponse = this.atm.getAccountService().updatePin(
                CardQueryModel.builder()
                        .cardNumber(this.atm.getCredential().getCardNumber())
                        .pin(this.atm.getCredential().getPin())
                        .newPin(newPin)
                        .build());

        if (cardDTOApiResponse.isSuccessful()) {

            System.out.println("You have successfully changed your PIN !");
            this.atm.setState(new DisplayTheCommandsState(this.atm));

        } else {
            this.atm.setState(new ErrorState(this.atm));
        }

    }
}
