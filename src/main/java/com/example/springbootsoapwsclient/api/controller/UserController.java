package com.example.springbootsoapwsclient.api.controller;

import com.example.springbootsoapwsclient.api.client.SoapClient;
import com.example.springbootsoapwsclient.api.user.UserRequest;
import com.example.springbootsoapwsclient.api.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

@RestController
public class UserController {

    @Autowired
    private SoapClient soapClient;

    @PostMapping("/getUser")
    public UserResponse getUser(@RequestPayload String name) {
        UserRequest request = new UserRequest();
        request.setName(name);
        return (UserResponse) soapClient.requestHandler(request);
    }

}
