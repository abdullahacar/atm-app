package com.atm.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class CardQueryModel extends QueryModel {

    private String cardNumber;
    private int balance;
    private int pin;
    private int newPin;


}
