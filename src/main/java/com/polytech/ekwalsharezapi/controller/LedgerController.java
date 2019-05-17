package com.polytech.ekwalsharezapi.controller;

import com.polytech.ekwalsharezapi.dto.LedgerDTO;
import com.polytech.ekwalsharezapi.dto.LedgerResponseDTO;
import com.polytech.ekwalsharezapi.dto.LedgerUserResponseDTO;
import com.polytech.ekwalsharezapi.model.Ledger;
import com.polytech.ekwalsharezapi.model.LedgerUser;
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
        LedgerResponseDTO ledgerDTO = modelMapper.map(ledger, LedgerResponseDTO.class);
        ledgerDTO.setUsers(ledger.getLedgerUser().stream().map(ledgerUser -> modelMapper.map(ledgerUser, LedgerUserResponseDTO.class)).collect(Collectors.toList()));
        return ledgerDTO;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation(value = "${LedgerController.getLedgers}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 401, message = "Invalid username/password supplied")})
    public List<LedgerResponseDTO> getLedgers(HttpServletRequest req) {
        List<Ledger> ledgers = ledgerService.getLedgers(req);
        List<LedgerResponseDTO> response =  new ArrayList<>();
        for (Ledger ledger: ledgers) {
            LedgerResponseDTO ledgerDTO = modelMapper.map(ledger, LedgerResponseDTO.class);
            ledgerDTO.setUsers(ledger.getLedgerUser().stream().map(ledgerUser -> modelMapper.map(ledgerUser, LedgerUserResponseDTO.class)).collect(Collectors.toList()));
            response.add(ledgerDTO);
        }
        return response;
    }



    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation(value = "${LedgerController.createLedger}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 401, message = "Invalid username/password supplied")})
    public void createLedger(@ApiParam("add Ledger") @RequestBody LedgerDTO ledgerDto, HttpServletRequest req) {
        Ledger ledger = modelMapper.map(ledgerDto, Ledger.class);
        ArrayList<LedgerUser> user = new ArrayList<>();
        ledgerDto.getUsers().stream().forEach(ledgerUserDTO -> user.add(modelMapper.map(ledgerUserDTO, LedgerUser.class)));
        ledger.setLedgerUser(user);
        ledgerService.createLedger(req, ledger);
    }
}
