package com.polytech.ekwalsharezapi.controller;

import com.polytech.ekwalsharezapi.dto.UserDataDTO;
import com.polytech.ekwalsharezapi.model.User;
import com.polytech.ekwalsharezapi.service.UserService;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Api(tags = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/signup")
    @ApiOperation(value = "${UserController.signup}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 401, message = "Username is already in use"),
            @ApiResponse(code = 403, message = "Access denied")})
    public String signup(@ApiParam("Signup User") @RequestBody UserDataDTO user) {
        return userService.signup(modelMapper.map(user, User.class));
    }

    @PostMapping("/signin")
    @ApiOperation(value = "${UserController.signin}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 401, message = "Invalid username/password supplied")})
    public String login(//
                        @ApiParam("Username") @RequestParam String username, //
                        @ApiParam("Password") @RequestParam String password) {
        return userService.signin(username, password);
    }

}
