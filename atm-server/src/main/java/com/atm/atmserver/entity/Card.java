package com.atm.atmserver.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "Card")
public class Card {

    @Id
    private String id;
    private String number;
    private int pin;
    private int balance;
    private String ownerId;

}