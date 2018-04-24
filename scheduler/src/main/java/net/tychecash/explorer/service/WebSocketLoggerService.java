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
public interface WebSocketLoggerService {

    //methods to register and unregister observers
    public void register(WebSocketObserverService observer);

    public void unregister(WebSocketObserverService observer);

    //method to notify observers of change
    public void notifyObservers();

    //method to get updates from subject
    public Object getUpdate(WebSocketObserverService observer);
}
