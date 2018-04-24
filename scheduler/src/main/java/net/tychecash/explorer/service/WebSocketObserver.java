/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.service;

/**
 *
 * @author jithin
 */
public class WebSocketObserver implements WebSocketObserverService{

    private String name;
    private WebSocketLoggerService topic;

    public WebSocketObserver(String nm) {
        this.name = nm;
    }

    @Override
    public void update() {
        String msg = (String) topic.getUpdate(this);
        if (msg == null) {
            System.out.println(name + ":: No new message");
        } else {
            System.out.println(name + ":: Consuming message::" + msg);
        }
    }

    @Override
    public void setSubject(WebSocketLogger logger) {
        this.topic = logger;
    }
}
