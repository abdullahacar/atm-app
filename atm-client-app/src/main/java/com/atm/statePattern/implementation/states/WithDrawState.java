package com.atm.statePattern.implementation.states;

import com.atm.dto.CardDTO;
import com.atm.dto.CardQueryModel;
import com.atm.dto.response.ApiResponse;
import com.atm.statePattern.abstraction.ATMState;

import java.util.Objects;

public class WithDrawState extends ATMState {

    public WithDrawState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void handleState() {

        int currentAmount = this.atm.getCurrentAmount();

        int balance = this.atm.getAccountService().getCardAndUser(

                CardQueryModel.builder().cardNumber(this.atm.getCredential().getCardNumber()).build()

        ).getEntity().getCard().getBalance();

        if (balance == 0) {
            System.out.println("Not enough balance for this operation !");
            this.atm.setState(new DisplayTheCommandsState(this.atm));
            return;
        }

        if (currentAmount == 0) {
            this.atm.setState(new OutOfCashState(this.atm));
        }

        System.out.println("Please enter the amount you would like to withdraw : ");
        int amount = 0;
        try {
            amount = this.atm.getSCANNER().nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input !");
            this.atm.getSCANNER().nextLine();
            this.atm.setState(this);
        }

        if (amount > balance) {

            System.out.println("It seems you have no enough cash ! Cancelling the operation !");
            this.atm.setState(new DisplayTheCommandsState(this.atm));
            return;

        }

        if (amount > currentAmount) {
            System.out.println("It seems atm has no enough cash for your demand. Would you like to withdraw the available ? Y/N");
            String answer = this.atm.getSCANNER().next();
            if (Objects.equals(answer, "Y")) {

                System.out.println("Please wait... Your cash is getting ready !");

                ApiResponse<CardDTO> cardDTOApiResponse = this.atm.getAccountService().updateBalance(

                        CardQueryModel.builder()
                                .cardNumber(this.atm.getCredential()
                                        .getCardNumber()).balance(balance - currentAmount)
                                .build()

                );

                if (cardDTOApiResponse.isSuccessful()) {
                    System.out.println("You have dispensed " + currentAmount);
                    this.atm.setCurrentAmount(0);
                    this.atm.setState(new DisplayTheCommandsState(this.atm));
                } else {
                    this.atm.setState(new ErrorState(this.atm, cardDTOApiResponse.getMessage()));
                }

            } else {
                this.atm.setState(new DisplayTheCommandsState(this.atm));
            }
        } else {

            System.out.println("Please wait... Your cash is getting ready !");

            ApiResponse<CardDTO> cardDTOApiResponse = this.atm.getAccountService().updateBalance(

                    CardQueryModel.builder()
                            .cardNumber(this.atm.getCredential().getCardNumber())
                            .balance(balance - amount)
                            .build()

            );

            if (cardDTOApiResponse.isSuccessful()) {
                System.out.println("You have dispensed " + amount);
                this.atm.setCurrentAmount(currentAmount - amount);
                this.atm.setState(new DisplayTheCommandsState(this.atm));
            } else {
                this.atm.setState(new ErrorState(this.atm, cardDTOApiResponse.getMessage()));
            }

        }

    }
}
