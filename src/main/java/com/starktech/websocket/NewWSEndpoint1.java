/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.websocket;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.websocket.OnMessage;

/**
 *
 * @author jolaadeadewale
 */
@ServerEndpoint("/endpointResource")
public class NewWSEndpoint1 {

    static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();
 
    @OnMessage
    public String onMessage(String message) {
        return null;
    }

}
