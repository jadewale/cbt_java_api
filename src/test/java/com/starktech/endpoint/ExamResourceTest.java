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
public class ExamResourceTest {
    
    public ExamResourceTest() {
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
     * Test of getJson method, of class ExamResource.
     */
    @Test
    public void testGetJson() {
        System.out.println("getJson");
        ExamResource instance = new ExamResource();
        String expResult = "";
        String result = instance.getJson();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of report method, of class ExamResource.
     */
    @Test
    public void testReport() {
        System.out.println("report");
        ExamResource instance = new ExamResource();
        String expResult = "";
        String result = instance.report();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getonGoingExamMethods method, of class ExamResource.
     */
    @Test
    public void testGetonGoingExamMethods() {
        System.out.println("getonGoingExamMethods");
        ExamResource instance = new ExamResource();
        String expResult = "";
        String result = instance.getonGoingExamMethods();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createExam method, of class ExamResource.
     */
    @Test
    public void testCreateExam() {
        System.out.println("createExam");
        String jsonObj = "";
        ExamResource instance = new ExamResource();
        String expResult = "";
        String result = instance.createExam(jsonObj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of scheduleExam method, of class ExamResource.
     */
    @Test
    public void testScheduleExam() {
        System.out.println("scheduleExam");
        String json = "";
        int status = 0;
        ExamResource instance = new ExamResource();
        String expResult = "";
        String result = instance.scheduleExam(json, status);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of queryExam method, of class ExamResource.
     */
    @Test
    public void testQueryExam() {
        System.out.println("queryExam");
        int status = 0;
        String subject = "";
        ExamResource instance = new ExamResource();
        String expResult = "";
        String result = instance.queryExam(status, subject);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of putJson method, of class ExamResource.
     */
    @Test
    public void testPutJson() {
        System.out.println("putJson");
        String content = "";
        ExamResource instance = new ExamResource();
        instance.putJson(content);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     
}
