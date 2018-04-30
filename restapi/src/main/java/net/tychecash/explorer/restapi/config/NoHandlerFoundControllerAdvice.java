/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.restapi.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 *
 * @author jithin
 */
@ControllerAdvice
public class NoHandlerFoundControllerAdvice {
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(Exception ex) {
        return "404";//this is view name
    }
}
