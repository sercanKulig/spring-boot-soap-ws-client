package com.example.springbootsoapwsclient.api.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class SoapClient extends WebServiceGatewaySupport {

    public Object requestHandler(Object request) {
        return getWebServiceTemplate().marshalSendAndReceive(request);
    }
}
