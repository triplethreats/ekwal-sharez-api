package com.polytech.ekwalsharezapi.service;

import com.polytech.ekwalsharezapi.dto.UpdatedLedgerDTO;
import com.polytech.ekwalsharezapi.exception.ApiException;
import com.polytech.ekwalsharezapi.model.Ledger;
import com.polytech.ekwalsharezapi.model.LedgerUser;
import com.polytech.ekwalsharezapi.model.Transaction;
import com.polytech.ekwalsharezapi.model.User;
import com.polytech.ekwalsharezapi.repository.*;
import com.polytech.ekwalsharezapi.security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LedgerService {

    @Autowired
    private LedgerRepository ledgerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private LedgerUserRepository ledgerUserRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ModelMapper modelMapper;

    public Ledger getLedger(HttpServletRequest request, Long ledgerId) {
        Long userId = userRepository.findByEmail(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request))).getId();
        return userRepository.findById(userId).getLedger().stream().filter(ledger -> ledger.getId().equals(ledgerId)).findFirst().orElseThrow(() -> new ApiException("Ledger not exists", HttpStatus.UNAUTHORIZED));
    }

    public List<Ledger> getLedgers(HttpServletRequest request) {
        return userRepository.findByEmail(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request))).getLedger();
    }

    public Long insertTransaction(HttpServletRequest request, Long ledgerId, Transaction transaction) {
        transaction.setLedger(getLedger(request, ledgerId));
        Transaction insert = transactionRepository.save(transaction);
        transaction.getPayment().stream().forEach(payment -> {
            payment.setUser(ledgerUserRepository.findById(payment.getUser().getId()));
            payment.setTransaction(insert);
            paymentRepository.save(payment);
        });
        return insert.getId();
    }

    public Long createLedger(HttpServletRequest req, Ledger ledger) {
        User user = userRepository.findByEmail(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        Ledger insert = ledgerRepository.save(ledger);
        ledger.getLedgerUser().stream().forEach(ledgerUser -> {
            ledgerUser.setLedger(insert);
            ledgerUserRepository.save(ledgerUser);
        });
        user.getLedger().add(insert);
        userRepository.save(user);
        return insert.getId();
    }

    public void putTransaction(HttpServletRequest req, Long ledgerId, Transaction transaction) {
        Ledger ledger = getLedger(req, ledgerId);
        ledger.getTransactions().stream().filter(transactionBDD -> transactionBDD.getId() == transaction.getId()).findFirst().orElseThrow(() -> new ApiException("Transaction not exists", HttpStatus.UNAUTHORIZED));
        transaction.setLedger(ledger);
        Transaction insert = transactionRepository.save(transaction);
        transaction.getPayment().stream().forEach(payment -> {
            payment.setTransaction(insert);
            paymentRepository.save(payment);
        });
    }

    public void updateLedger(HttpServletRequest req, UpdatedLedgerDTO ledgerDto) {
        Ledger ledger = getLedger(req, ledgerDto.getUpdatedLedger().getId());
        ledger.setDescription(ledgerDto.getUpdatedLedger().getDescription());
        ledger.setTitle(ledgerDto.getUpdatedLedger().getTitle());

        List<LedgerUser> ledgerUsers = ledgerDto.getUpdatedLedger().getUsers().stream().map(ledgerUserDTO -> {
            LedgerUser user = modelMapper.map(ledgerUserDTO, LedgerUser.class);
            user.setLedger(ledger);
            ledgerUserRepository.save(user);
            return user;
        }).collect(Collectors.toList());

        ledgerUsers.addAll(ledgerDto.getNewUsers().stream().map(ledgerUserDTO -> {
            LedgerUser user = modelMapper.map(ledgerUserDTO, LedgerUser.class);
            user.setLedger(ledger);
            ledgerUserRepository.save(user);
            return user;
        }).collect(Collectors.toList()));

        ledger.setLedgerUser(ledgerUsers);
        ledgerRepository.save(ledger);
    }
}
