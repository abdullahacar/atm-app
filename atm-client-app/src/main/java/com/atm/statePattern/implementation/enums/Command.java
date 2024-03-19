package com.atm.statePattern.implementation.enums;

import java.util.Arrays;

public enum Command {

    CHANGE_PIN(0, "Change PIN"),
    LOG_OUT(1, "Log Out"),
    VIEW_BALANCE(2, "View Balance"),
    WITHDRAW(3, "Withdraw Money"),
    DEPOSIT(4, "Deposit");

    int value;

    String desc;

    Command(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static Command get(int value) {
        return Arrays.stream(Command.values()).filter(command -> command.getValue() == value).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "(" + value + ") " + this.desc;
    }
}
