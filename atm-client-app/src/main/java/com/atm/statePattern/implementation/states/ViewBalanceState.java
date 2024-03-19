package com.atm.statePattern.implementation.states;

import com.atm.dto.CardAndUserDTO;
import com.atm.dto.CardQueryModel;
import com.atm.dto.response.ApiResponse;
import com.atm.statePattern.abstraction.ATMState;

public class ViewBalanceState extends ATMState {

    public ViewBalanceState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void handleState() {
        ApiResponse<CardAndUserDTO> cardAndUser = this.atm.getAccountService().getCardAndUser(CardQueryModel.builder().cardNumber(this.atm.getCredential().getCardNumber()).build());

        if (cardAndUser.isNotSuccessful()) {
            this.atm.setState(new ErrorState(this.atm));
            return;
        }
        System.out.println("Your balance : " + cardAndUser.getEntity().getCard().getBalance());
        this.atm.setState(new DisplayTheCommandsState(this.atm));
    }
}
