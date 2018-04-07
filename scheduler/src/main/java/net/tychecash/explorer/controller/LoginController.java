/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.controller;

import java.security.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jithin
 */
@Controller
public class LoginController {

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView welcomePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return model;
    }

    @RequestMapping(value = {"/loginPage"}, method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid Credentials provided.");
        }
        model.setViewName("index");
        return model;
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public ModelAndView logoutPage(@RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        if (logout != null) {
            model.addObject("message", "Logged out successfully.");
        }
        model.setViewName("index");
        return model;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView homePage() {

        ModelAndView model = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addObject("username", userDetails.getUsername());
            model.setViewName("dashboard");
        } else {
            model.setViewName("index");
        }
        return model;
    }

}
