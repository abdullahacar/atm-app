package com.atm.statePattern.implementation.states;

import com.atm.dto.CardAndUserDTO;
import com.atm.dto.CardQueryModel;
import com.atm.dto.UserDTO;
import com.atm.dto.response.ApiResponse;
import com.atm.statePattern.abstraction.ATMState;

import java.util.Scanner;

public class InitialState extends ATMState {

    public InitialState(ATM atm) {

        this.atm = atm;
        this.atm.setLoggedIn(false);

    }

    @Override
    public void handleState() {

        System.out.println("Please enter your card number : ");

        Scanner scanner = this.atm.getSCANNER();

        String cardNumber = scanner.next();

        ApiResponse<CardAndUserDTO> cardAndUserResponse = this.atm.getAccountService().getCardAndUser(

                CardQueryModel.builder()
                        .cardNumber(cardNumber)
                        .build()

        );

        if (cardAndUserResponse.isNotSuccessful()) {
            System.out.println(cardAndUserResponse.getApiResponseCode() + " : " + cardAndUserResponse.getMessage());
            this.atm.setState(new ErrorState(this.atm));
        }

        CardAndUserDTO entity = cardAndUserResponse.getEntity();

        UserDTO user = entity.getUser();

        System.out.println("Welcome " + user.getFirstName() + " " + user.getLastName() + " ! ");

        atm.setState(new CardNumberEnteredState(atm, cardNumber));

    }
}
