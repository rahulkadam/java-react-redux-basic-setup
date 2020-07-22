package com.apg.payment.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping(value = "/get")
    @ResponseBody
    public String getUserName() {
        return "UserName: Rahul";
    }

    @GetMapping(value = "/getfullName")
    @ResponseBody
    public String getUserFUllName() {
        return "UserName: Rahul Kadam";
    }

}
