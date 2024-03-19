package com.atm.atmserver.mapper;

import com.atm.atmserver.dto.CardDTO;
import com.atm.atmserver.dto.UserDTO;
import com.atm.atmserver.entity.Card;
import com.atm.atmserver.entity.User;

public class Mapper {


    public static CardDTO toCardDTOFrom(Card card) {

        return CardDTO.builder()
                .id(card.getId())
                .number(card.getNumber())
                .pin(card.getPin())
                .balance(card.getBalance())
                .ownerId(card.getOwnerId())
                .build();

    }

    public static Card toCardFrom(CardDTO card) {

        return Card.builder()
                .id(card.getId())
                .number(card.getNumber())
                .pin(card.getPin())
                .balance(card.getBalance())
                .ownerId(card.getOwnerId())
                .build();

    }

    public static UserDTO toUserDTOFrom(User user) {

        return UserDTO.builder().id(user.getId()).firstName(user.getFirstName()).lastName(user.getLastName()).build();

    }

    public static User toUserFrom(UserDTO user) {

        return User.builder().id(user.getId()).firstName(user.getFirstName()).lastName(user.getLastName()).build();

    }

}
