package com.atm.service.fakeServer;

import com.atm.dto.LoginCredential;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

public class AccountService {

    private static HashMap<String, Integer> cardNumberPinMap = new HashMap<>();

    private static HashMap<String, Integer> cardNumberBalanceMap = new HashMap<>();

    private static HashSet<String> loggedInCards = new HashSet<>();

    static {

        cardNumberPinMap.put("1", 1234);

        cardNumberBalanceMap.put("1", 1250);

    }

    public boolean validateCardAndLogin(LoginCredential credential) {

        Optional<Integer> s = Optional.ofNullable(cardNumberPinMap.get(credential.getCardNumber()));

        boolean valid = s.map(value -> value.equals(credential.getPin())).orElse(false);

        if (valid) {
            loggedInCards.add(credential.getCardNumber());
        }

        return valid;

    }

    public int getBalance(LoginCredential credential) {

        Optional<Integer> s = Optional.ofNullable(cardNumberPinMap.get(credential.getCardNumber()));

        return cardNumberBalanceMap.get(credential.getCardNumber());

    }

    public boolean updateBalance(LoginCredential credential, int newBalance) {

        cardNumberBalanceMap.put(credential.getCardNumber(), newBalance);

        return true;

    }

    public boolean logOut(LoginCredential credential){

        loggedInCards.remove(credential.getCardNumber());

        return true;

    }

    public boolean changePin(LoginCredential credential, int newPin) {

        cardNumberPinMap.put(credential.getCardNumber(), newPin);

        return true;

    }
}
