package com.atm.atmserver.service;

import com.atm.atmserver.dto.*;

public interface IAccountServiceOperations {

    ApiResponse<CardAndUserDTO> getCardAndUser(CardQueryModel queryModel);

    ApiResponse<CardDTO> updateBalance(CardQueryModel queryModel);

    ApiResponse<CardDTO> updatePin(CardQueryModel queryModel);

    ApiResponse<CardDTO> saveCard(CardDTO card);

    ApiResponse<UserDTO> saveUser(UserDTO user);

    ApiResponse<CardDTO> validatePin(CardQueryModel queryModel);

}
