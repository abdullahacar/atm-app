package com.atm.atmserver.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("Token")
public class Token {

  @Id
  String _id;
  String tokenKey;
  Date issueDate;
  Date lastUpdate;
  String cardId;
  boolean active = true;

}
