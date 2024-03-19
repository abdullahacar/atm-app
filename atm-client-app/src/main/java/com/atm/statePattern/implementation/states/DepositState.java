package com.atm.statePattern.implementation.states;

import com.atm.dto.CardAndUserDTO;
import com.atm.dto.CardDTO;
import com.atm.dto.CardQueryModel;
import com.atm.dto.response.ApiResponse;
import com.atm.service.AccountService;
import com.atm.statePattern.abstraction.ATMState;

public class DepositState extends ATMState {

    public DepositState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void handleState() {

        System.out.println("Please enter the deposit amount :");
        int depositAmount = 0;
        try {
            depositAmount = this.atm.getSCANNER().nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input !");
            this.atm.SCANNER.nextLine();
            this.atm.setState(this);
        }
        AccountService accountService = this.atm.getAccountService();

        ApiResponse<CardAndUserDTO> cardAndUser = accountService.getCardAndUser(CardQueryModel.builder().cardNumber(this.atm.getCredential().getCardNumber()).build());

        if (cardAndUser.isNotSuccessful()) {
            this.atm.setState(new ErrorState(this.atm, cardAndUser.getMessage()));
            return;
        }

        int newBalance = cardAndUser.getEntity().getCard().getBalance() + depositAmount;

        ApiResponse<CardDTO> cardDTOApiResponse = accountService.updateBalance(CardQueryModel.builder().cardNumber(this.atm.getCredential().getCardNumber()).balance(newBalance).build());

        if (cardDTOApiResponse.isSuccessful()) {

            this.atm.setCurrentAmount(this.atm.currentAmount + depositAmount);
            System.out.println("It is managed successfully !");
            int updatedBalance = accountService.getCardAndUser(CardQueryModel.builder().cardNumber(this.atm.getCredential().getCardNumber()).build()).getEntity().getCard().getBalance();
            System.out.println("You have now " + updatedBalance + " in your account !");
            this.atm.setState(new DisplayTheCommandsState(this.atm));

        } else {

            System.out.println("Your deposit being refunded...");
            this.atm.setState(new ErrorState(this.atm));

        }

    }
}
