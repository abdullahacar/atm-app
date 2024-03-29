package com.atm.atmserver.repository;

import com.atm.atmserver.entity.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  CardRepository extends MongoRepository<Card, String> {

    Optional<Card> findByNumber(String cardNumber);

}
