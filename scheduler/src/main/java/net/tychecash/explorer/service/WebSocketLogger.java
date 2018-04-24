/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jithin
 */
public class WebSocketLogger implements WebSocketLoggerService {

    private List<WebSocketObserverService> observers;
    private String message;
    private boolean changed;
    private final Object MUTEX = new Object();

    public WebSocketLogger() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void register(WebSocketObserverService obj) {
        if (obj == null) {
            throw new NullPointerException("Null Observer");
        }
        synchronized (MUTEX) {
            if (!observers.contains(obj)) {
                observers.add(obj);
            }
        }
    }

    @Override
    public void unregister(WebSocketObserverService obj) {
        synchronized (MUTEX) {
            observers.remove(obj);
        }
    }

    @Override
    public void notifyObservers() {
        List<WebSocketObserverService> observersLocal = null;
        //synchronization is used to make sure any observer registered after message is received is not notified
        synchronized (MUTEX) {
            if (!changed) {
                return;
            }
            observersLocal = new ArrayList<>(this.observers);
            this.changed = false;
        }
        for (WebSocketObserverService obj : observersLocal) {
            obj.update();
        }

    }

    @Override
    public Object getUpdate(WebSocketObserverService obj) {
        return this.message;
    }

    //method to post message to the topic
    public void postMessage(String msg) {
        System.out.println("Message Posted to Topic:" + msg);
        this.message = msg;
        this.changed = true;
        notifyObservers();
    }

}
