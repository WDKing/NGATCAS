/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adam
 */
public class WarningLevelCalculatorTest {
    
    public WarningLevelCalculatorTest() {
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
     * Test of parseList method, of class WarningLevelCalculator.
     */
    @Test
    public void testParseList() {
        System.out.println("parseList");
        WarningLevelCalculator instance = new WarningLevelCalculator();
        instance.parseList();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of detectCollision method, of class WarningLevelCalculator.
     */
    @Test
    public void testDetectCollision() {
        System.out.println("detectCollision");
        Aircraft one = null;
        Aircraft two = null;
        WarningLevelCalculator instance = new WarningLevelCalculator();
        Collision expResult = null;
        Collision result = instance.detectCollision(one, two);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testCollisionPoint method, of class WarningLevelCalculator.
     */
    @Test
    public void testTestCollisionPoint() {
        System.out.println("testCollisionPoint");
        Collision collision = null;
        WarningLevelCalculator instance = new WarningLevelCalculator();
        boolean expResult = false;
        boolean result = instance.testCollisionPoint(collision);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWarningLevelYellow method, of class WarningLevelCalculator.
     */
    @Test
    public void testSetWarningLevelYellow() {
        System.out.println("setWarningLevelYellow");
        Aircraft plane = null;
        WarningLevelCalculator instance = new WarningLevelCalculator();
        instance.setWarningLevelYellow(plane);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWarningLevelOrange method, of class WarningLevelCalculator.
     */
    @Test
    public void testSetWarningLevelOrange() {
        System.out.println("setWarningLevelOrange");
        Aircraft plane = null;
        WarningLevelCalculator instance = new WarningLevelCalculator();
        instance.setWarningLevelOrange(plane);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWarningLevelRed method, of class WarningLevelCalculator.
     */
    @Test
    public void testSetWarningLevelRed() {
        System.out.println("setWarningLevelRed");
        Aircraft plane = null;
        WarningLevelCalculator instance = new WarningLevelCalculator();
        instance.setWarningLevelRed(plane);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
