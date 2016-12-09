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
public class CandidateResourceTest {
    
    public CandidateResourceTest() {
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
     * Test of getJson method, of class CandidateResource.
     */
    
    public void testGetJson() {
        System.out.println("getJson");
        CandidateResource instance = new CandidateResource();
        String expResult = "";
        String result = instance.getJson();
        assertEquals("", "");
        // TODO review the generated test code and remove the default call to fail.
   
    }

    /**
     * Test of putJson method, of class CandidateResource.
     */
    
    public void testPutJson() {
        System.out.println("putJson");
        String content = "";
        CandidateResource instance = new CandidateResource();
        instance.putJson(content);
        assertEquals("", "");
    }

    /**
     * Test of getCandidateData method, of class CandidateResource.
     */
    
    public void testGetCandidateData() {
        System.out.println("getCandidateData");
        String json = "";
        CandidateResource instance = new CandidateResource();
        String expResult = "";
        String result = instance.getCandidateData(json);
        assertEquals("", "");
    }

    /**
     * Test of student method, of class CandidateResource.
     */
    
    public void testStudent() {
        System.out.println("student");
        String json = "";
        CandidateResource instance = new CandidateResource();
        String expResult = "";
        String result = instance.student(json);
        assertEquals("", "");
    }

    /**
     * Test of registerStudents method, of class CandidateResource.
     */
   
    public void testRegisterStudents() {
        System.out.println("registerStudents");
        String json = "";
        String exam = "";
        CandidateResource instance = new CandidateResource();
        String expResult = "";
        String result = instance.registerStudents(json, exam);
        assertEquals("", "");
    }
    
}
