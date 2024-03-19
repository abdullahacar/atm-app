package com.atm.statePattern.implementation.states;

import com.atm.config.ApplicationBean;
import com.atm.service.AccountService;
import com.atm.dto.LoginCredential;
import com.atm.statePattern.abstraction.ATMState;
import com.atm.statePattern.abstraction.IAtmOperation;

import java.util.Scanner;

public class ATM implements IAtmOperation {

    private LoginCredential credential;

    private boolean loggedIn;

    protected Scanner SCANNER;

    private ATMState currentState;

    private AccountService accountService;

    int currentAmount;

    public ATM(int initialAmount) {
        this.accountService = ApplicationBean.getInstance().getAccountService();
        this.currentAmount = initialAmount;
        this.SCANNER = new Scanner(System.in);
        this.setState(new InitialState((this)));
    }

    void setState(ATMState newState) {
        this.currentState = newState;
        this.currentState.handleState();
    }

    public LoginCredential getCredential() {
        if (!loggedIn) {
            System.out.println("It seems you are not logged in !");
            setState(new InitialState((this)));
            return null;
        }
        return credential;
    }

    protected void setCredential(LoginCredential credential) {
        this.credential = credential;
    }

    @Override
    public void login(LoginCredential credential) {
        if (loggedIn) {
            setState(new DisplayTheCommandsState(this));
        }
        setState(new LoggingInState(this, credential));
    }

    @Override
    public void logOut(LoginCredential credential) {
        if (!loggedIn) {
            setState(new InitialState(this));
            return;
        }
        setState(new LogOutState(this));
    }

    @Override
    public void changePin(String newPin) {
        if (!loggedIn) {
            setState(new InitialState(this));
        }
        setState(new ChangePinState(this));
    }

    @Override
    public void viewAvailableBalance() {
        if (!loggedIn) {
            setState(new InitialState(this));
        }
        setState(new ViewBalanceState(this));
    }

    @Override
    public void withDraw(int amount) {
        if (!loggedIn) {
            setState(new InitialState(this));
        }
        setState(new WithDrawState(this));
    }

    @Override
    public void deposit(int amount) {
        if (!loggedIn) {
            setState(new InitialState(this));
        }
        setState(new DepositState(this));
    }

    protected AccountService getAccountService() {
        return accountService;
    }


    protected boolean isLoggedIn() {
        return loggedIn;
    }

    protected void setLoggedIn(boolean loggedIn) {
        if (!loggedIn && credential != null) {
            this.credential = null;
        }
        this.loggedIn = loggedIn;
    }

    protected int getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(int currentAmount) {
        this.currentAmount = currentAmount;
    }

    protected Scanner getSCANNER() {
        return SCANNER;
    }
}
