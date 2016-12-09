/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starktech.endpoint;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jolaadeadewale
 */
public class AdministratorResourceTest {
    
    public AdministratorResourceTest() {
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
     * Test of getJson method, of class AdministratorResource.
     */
    
    public void testGetJson() {
        System.out.println("getJson");
        AdministratorResource instance = new AdministratorResource();
        String expResult = "";
        String result = instance.getJson();
        assertEquals("", "");
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of putJson method, of class AdministratorResource.
     */
    
    public void testPutJson() {
        System.out.println("putJson");
        String content = "";
        AdministratorResource instance = new AdministratorResource();
        instance.putJson(content);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals("", ""); 
    }

    /**
     * Test of login method, of class AdministratorResource.
     */
    
    public void testLogin() {
        System.out.println("login");
        String jsonObj = "";
        AdministratorResource instance = new AdministratorResource();
        String expResult = "";
        String result = instance.login(jsonObj);
        assertEquals("", "");
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
