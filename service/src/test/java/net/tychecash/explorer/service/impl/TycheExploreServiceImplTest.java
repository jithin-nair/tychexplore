/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.service.impl;

import net.tychecash.explorer.model.CountVO;
import net.tychecash.explorer.model.ResponseVO;
import net.tychecash.explorer.model.response.BlockResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jithin
 */
public class TycheExploreServiceImplTest {
    
    public TycheExploreServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getBlockResponseByHeight method, of class TycheExploreServiceImpl.
     */
    @Test
    public void testGetBlockResponseByHeight() {
        System.out.println("getBlockResponseByHeight");
        Integer height = null;
        TycheExploreServiceImpl instance = new TycheExploreServiceImpl();
        BlockResponse expResult = null;
        BlockResponse result = instance.getBlockResponseByHeight(height);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBlockResponseByHash method, of class TycheExploreServiceImpl.
     */
    @Test
    public void testGetBlockResponseByHash() {
        System.out.println("getBlockResponseByHash");
        String hash = "";
        TycheExploreServiceImpl instance = new TycheExploreServiceImpl();
        BlockResponse expResult = null;
        BlockResponse result = instance.getBlockResponseByHash(hash);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstBlockResponse method, of class TycheExploreServiceImpl.
     */
    @Test
    public void testGetFirstBlockResponse() {
        System.out.println("getFirstBlockResponse");
        TycheExploreServiceImpl instance = new TycheExploreServiceImpl();
        BlockResponse expResult = null;
        BlockResponse result = instance.getFirstBlockResponse();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastBlockResponse method, of class TycheExploreServiceImpl.
     */
    @Test
    public void testGetLastBlockResponse() {
        System.out.println("getLastBlockResponse");
        TycheExploreServiceImpl instance = new TycheExploreServiceImpl();
        BlockResponse expResult = null;
        BlockResponse result = instance.getLastBlockResponse();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastNBlockResponseFromHeight method, of class TycheExploreServiceImpl.
     */
    @Test
    public void testGetLastNBlockResponseFromHeight() {
        System.out.println("getLastNBlockResponseFromHeight");
        Integer height = null;
        Integer pageNumber = null;
        Integer size = null;
        TycheExploreServiceImpl instance = new TycheExploreServiceImpl();
        ResponseVO expResult = null;
        ResponseVO result = instance.getLastNBlockResponseFromHeight(height, pageNumber, size);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBlockSamples method, of class TycheExploreServiceImpl.
     */
    @Test
    public void testGetBlockSamples() {
        System.out.println("getBlockSamples");
        Integer samplingRate = null;
        TycheExploreServiceImpl instance = new TycheExploreServiceImpl();
        ResponseVO expResult = null;
        ResponseVO result = instance.getBlockSamples(samplingRate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBlockCount method, of class TycheExploreServiceImpl.
     */
    @Test
    public void testGetBlockCount() {
        System.out.println("getBlockCount");
        TycheExploreServiceImpl instance = new TycheExploreServiceImpl();
        CountVO expResult = null;
        CountVO result = instance.getBlockCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
