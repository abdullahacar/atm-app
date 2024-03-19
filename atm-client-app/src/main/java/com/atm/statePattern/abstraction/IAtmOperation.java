package com.atm.statePattern.abstraction;


import com.atm.dto.LoginCredential;

public interface IAtmOperation {

    void login(LoginCredential credential);

    void logOut(LoginCredential credential);

    void changePin(String newPin);

    void viewAvailableBalance();

    void withDraw(int amount);

    void deposit(int amount);

}
