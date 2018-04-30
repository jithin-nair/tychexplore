/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.restapi.service.impl;

import junit.framework.TestCase;

/**
 *
 * @author jithin
 */
public class RestApiServiceImplTest extends TestCase {
    
    public RestApiServiceImplTest(String testName) {
        super(testName);
    }

    /**
     * Test of getTotalMinedCoins method, of class RestApiServiceImpl.
     */
    public void testGetTotalMinedCoins() {
        System.out.println("getTotalMinedCoins");
        RestApiServiceImpl instance = new RestApiServiceImpl();
        String expResult = "";
        String result = instance.getTotalMinedCoins();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalMinedCoinsInWords method, of class RestApiServiceImpl.
     */
    public void testGetTotalMinedCoinsInWords() {
        System.out.println("getTotalMinedCoinsInWords");
        RestApiServiceImpl instance = new RestApiServiceImpl();
        String expResult = "";
        String result = instance.getTotalMinedCoinsInWords();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
