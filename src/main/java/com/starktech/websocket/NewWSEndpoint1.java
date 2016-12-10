/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.websocket;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author jolaadeadewale
 */
@ServerEndpoint("/endpointResource")
public class NewWSEndpoint1 {

    @OnMessage
    public String onMessage(String message) {
        return null;
    }
    
}
