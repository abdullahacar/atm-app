package com.atm.atmserver;

import com.atm.atmserver.dto.ApiResponse;
import com.atm.atmserver.dto.CardDTO;
import com.atm.atmserver.dto.UserDTO;
import com.atm.atmserver.entity.Card;
import com.atm.atmserver.repository.CardRepository;
import com.atm.atmserver.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class AtmServerApplication implements CommandLineRunner {

    @Autowired
    AccountService accountService;

    @Autowired
    private CardRepository cardRepository;

    public static void main(String[] args) {
        SpringApplication.run(AtmServerApplication.class, args);
    }

    @Override
    public void run(String... args) {

        Optional<Card> testCard = cardRepository.findByNumber("1");

        if (testCard.isEmpty()) {

            ApiResponse<UserDTO> userDTOApiResponse = accountService.saveUser(
                    UserDTO.builder()
                            .firstName("John")
                            .lastName("Doe")
                            .build()
            );

            if (userDTOApiResponse.isSuccessful()) {

                UserDTO insertedUser = userDTOApiResponse.getEntity();

                System.out.println("Inserted user {} : " + insertedUser);

                ApiResponse<CardDTO> cardDTOApiResponse = accountService.saveCard(
                        CardDTO.builder()
                                .number("1")
                                .pin(1234)
                                .balance(5000)
                                .ownerId(insertedUser.getId())
                                .build()

                );

                if (cardDTOApiResponse.isSuccessful()) {

                    CardDTO insertedCard = cardDTOApiResponse.getEntity();

                    System.out.println("Inserted Card {} : " + insertedCard);

                }

            }


        }


    }
}
