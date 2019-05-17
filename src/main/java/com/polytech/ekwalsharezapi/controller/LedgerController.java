package com.polytech.ekwalsharezapi.controller;

import com.polytech.ekwalsharezapi.dto.*;
import com.polytech.ekwalsharezapi.model.Ledger;
import com.polytech.ekwalsharezapi.model.LedgerUser;
import com.polytech.ekwalsharezapi.model.Payment;
import com.polytech.ekwalsharezapi.model.Transaction;
import com.polytech.ekwalsharezapi.service.LedgerService;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ledgers")
@Api(tags = "ledgers")
public class LedgerController {

    @Autowired
    private LedgerService ledgerService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{ledgerId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation(value = "${LedgerController.getLedger}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 401, message = "Invalid username/password supplied")})
    public LedgerResponseDTO getLedger(@PathVariable(value = "ledgerId") Long ledgerId, HttpServletRequest req) {
        Ledger ledger = ledgerService.getLedger(req, ledgerId);
        return createDTO(ledger);
    }


    @PostMapping("/{ledgerId}/transactions")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation(value = "${LedgerController.insertTransaction}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 401, message = "Invalid username/password supplied")})
    public Long insertTransaction(@PathVariable(value = "ledgerId") Long ledgerId, HttpServletRequest req, @RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
        ArrayList<Payment> payments = new ArrayList<>();
        transactionDTO.getPayments().stream().forEach(paymentDTO -> payments.add(modelMapper.map(paymentDTO, Payment.class)));
        transaction.setPayment(payments);
        return ledgerService.insertTransaction(req, ledgerId, transaction);
    }

    @PutMapping("/{ledgerId}/transactions/{transactionId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation(value = "${LedgerController.putTransaction}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 401, message = "Invalid username/password supplied")})
    public void putTransaction(@PathVariable(value = "ledgerId") Long ledgerId, HttpServletRequest req, @RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
        ArrayList<Payment> payments = new ArrayList<>();
        transactionDTO.getPayments().stream().forEach(paymentDTO -> payments.add(modelMapper.map(paymentDTO, Payment.class)));
        transaction.setPayment(payments);
        ledgerService.putTransaction(req, ledgerId, transaction);
    }


    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation(value = "${LedgerController.getLedgers}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 401, message = "Invalid username/password supplied")})
    public List<LedgerResponseDTO> getLedgers(HttpServletRequest req) {
        List<Ledger> ledgers = ledgerService.getLedgers(req);
        List<LedgerResponseDTO> response = new ArrayList<>();
        for (Ledger ledger : ledgers) {
            response.add(createDTO(ledger));
        }
        return response;
    }


    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation(value = "${LedgerController.createLedger}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 401, message = "Invalid username/password supplied")})
    public Long createLedger(@ApiParam("add Ledger") @RequestBody LedgerDTO ledgerDto, HttpServletRequest req) {
        Ledger ledger = modelMapper.map(ledgerDto, Ledger.class);
        ArrayList<LedgerUser> user = new ArrayList<>();
        ledgerDto.getUsers().stream().forEach(ledgerUserDTO -> user.add(modelMapper.map(ledgerUserDTO, LedgerUser.class)));
        ledger.setLedgerUser(user);
        return ledgerService.createLedger(req, ledger);
    }

    @PutMapping("/{ledgerId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation(value = "${LedgerController.createLedger}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 401, message = "Invalid username/password supplied")})
    public void putLedger(@ApiParam("add Ledger") @RequestBody UpdatedLedgerDTO ledgerDto, HttpServletRequest req) {

        ledgerService.updateLedger(req, ledgerDto);
    }


    private LedgerResponseDTO createDTO(Ledger ledger) {
        LedgerResponseDTO ledgerDTO = modelMapper.map(ledger, LedgerResponseDTO.class);
        ledgerDTO.setUsers(ledger.getLedgerUser().stream().map(ledgerUser -> modelMapper.map(ledgerUser, LedgerUserResponseDTO.class)).collect(Collectors.toList()));

        if(ledger.getLedgerUser() != null){
            ledgerDTO.setTransactions(ledger.getTransactions().stream().map(transaction -> {
                TransactionResponseDTO response = modelMapper.map(transaction, TransactionResponseDTO.class);
                response.setPayments(transaction.getPayment().stream().map(payment -> modelMapper.map(payment, PaymentResponseDTO.class)).collect(Collectors.toList()));
                return response;
            }).collect(Collectors.toList()));
        } else {
            ledgerDTO.setTransactions(new ArrayList<>());
        }


        return ledgerDTO;
    }
}
