package com.atm.atmserver.controller;

import com.atm.atmserver.dto.ApiResponse;
import com.atm.atmserver.dto.CardAndUserDTO;
import com.atm.atmserver.dto.CardDTO;
import com.atm.atmserver.dto.CardQueryModel;
import com.atm.atmserver.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/getCardAndUser")
    public ResponseEntity<ApiResponse<CardAndUserDTO>> getCardAndUser(@RequestBody CardQueryModel queryModel) {

        return ResponseEntity.ok(accountService.getCardAndUser(queryModel));

    }

    @PostMapping("/updateBalance")
    public ResponseEntity<ApiResponse<CardDTO>> updateBalance(@RequestBody CardQueryModel queryModel) {

        return ResponseEntity.ok(accountService.updateBalance(queryModel));

    }

    @PostMapping("/updatePin")
    public ResponseEntity<ApiResponse<CardDTO>> updatePin(@RequestBody CardQueryModel queryModel) {

        return ResponseEntity.ok(accountService.updatePin(queryModel));

    }

    @PostMapping("/validatePin")
    public ResponseEntity<ApiResponse<CardDTO>> validatePin(@RequestBody CardQueryModel queryModel) {

        return ResponseEntity.ok(accountService.validatePin(queryModel));

    }


}
