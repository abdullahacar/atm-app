package com.atm.atmserver.service;

import com.atm.atmserver.dto.*;
import com.atm.atmserver.entity.Card;
import com.atm.atmserver.entity.User;
import com.atm.atmserver.mapper.Mapper;
import com.atm.atmserver.repository.CardRepository;
import com.atm.atmserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountServiceOperations {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ApiResponse<CardAndUserDTO> getCardAndUser(CardQueryModel queryModel) {

        try {
            Optional<Card> cardOptional = cardRepository.findByNumber(queryModel.getCardNumber());

            if (cardOptional.isEmpty())
                return ApiResponseGenerator.warning("No card found !");

            String ownerId = cardOptional.get().getOwnerId();

            Optional<User> userById = userRepository.findById(ownerId);

            if (userById.isPresent()) {

                Card card = cardOptional.get();

                User user = userById.get();

                CardAndUserDTO cardAndUserDTO = CardAndUserDTO.builder()
                        .card(Mapper.toCardDTOFrom(card))
                        .user(Mapper.toUserDTOFrom(user))
                        .build();

                return ApiResponse.<CardAndUserDTO>builder().entity(cardAndUserDTO).apiResponseCode(ApiResponseCode.SUCCESSFUL).build();

            } else {

                return ApiResponseGenerator.warning("User not found !");

            }
        } catch (Exception e) {
            return ApiResponseGenerator.exception(e);
        }

    }

    @Override
    public ApiResponse<CardDTO> updateBalance(CardQueryModel queryModel) {

        try {
            Optional<Card> card = cardRepository.findByNumber(queryModel.getCardNumber());

            if (card.isEmpty())
                return ApiResponseGenerator.warning("Card not found !");

            card.get().setBalance(queryModel.getBalance());

            Card savedCard = cardRepository.save(card.get());

            return ApiResponse.<CardDTO>builder()
                    .apiResponseCode(ApiResponseCode.SUCCESSFUL)
                    .entity(Mapper.toCardDTOFrom(savedCard))
                    .build();

        } catch (Exception e) {

            return ApiResponseGenerator.exception(e);

        }

    }

    @Override
    public ApiResponse<CardDTO> updatePin(CardQueryModel queryModel) {

        try {
            Optional<Card> card = cardRepository.findByNumber(queryModel.getCardNumber());

            if (card.isEmpty())
                return ApiResponseGenerator.warning("Card not found !");

            card.get().setPin(queryModel.getNewPin());

            Card savedCard = cardRepository.save(card.get());

            return ApiResponse.<CardDTO>builder()
                    .apiResponseCode(ApiResponseCode.SUCCESSFUL)
                    .entity(Mapper.toCardDTOFrom(savedCard))
                    .build();
        } catch (Exception e) {
            return ApiResponseGenerator.exception(e);
        }

    }

    @Override
    public ApiResponse<CardDTO> saveCard(CardDTO card) {

        try {
            Card savedCard = cardRepository.save(Card.builder()
                    .id(card.getId())
                    .number(card.getNumber())
                    .pin(card.getPin())
                    .balance(card.getBalance())
                    .ownerId(card.getOwnerId())
                    .build());

            return ApiResponse.<CardDTO>builder()
                    .apiResponseCode(ApiResponseCode.SUCCESSFUL)
                    .entity(Mapper.toCardDTOFrom(savedCard))
                    .build();

        } catch (Exception e) {
            return ApiResponseGenerator.exception(e);

        }
    }

    @Override
    public ApiResponse<UserDTO> saveUser(UserDTO user) {
        try {
            User savedUser = userRepository.save(User.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .build());

            return ApiResponse.<UserDTO>builder()
                    .apiResponseCode(ApiResponseCode.SUCCESSFUL)
                    .entity(Mapper.toUserDTOFrom(savedUser))
                    .build();

        } catch (Exception e) {
            return ApiResponseGenerator.exception(e);

        }
    }

    @Override
    public ApiResponse<CardDTO> validatePin(CardQueryModel queryModel) {

        try {
            Optional<Card> card = cardRepository.findByNumber(queryModel.getCardNumber());

            if (card.isEmpty())
                return ApiResponse.<CardDTO>builder().apiResponseCode(ApiResponseCode.WARNING).message("Card not found !").build();

            boolean isOK = card.get().getPin() == queryModel.getPin();

            if (isOK) {

                return ApiResponse.<CardDTO>builder()
                        .apiResponseCode(ApiResponseCode.SUCCESSFUL)
                        .entity(Mapper.toCardDTOFrom(card.get()))
                        .build();

            }

            return ApiResponseGenerator.warning("Not valid PIN !");

        } catch (Exception e) {

            return ApiResponseGenerator.exception(e);
        }

    }

}
