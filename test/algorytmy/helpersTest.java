/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package algorytmy;

import java.awt.image.BufferedImage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Greko
 */
public class helpersTest {
    
    public helpersTest() {
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
     * Test of zwrocMaske method, of class helpers.
     */
    @Test
    public void testZwrocMaske() {
        System.out.println("zwrocMaske");
        int rozmiarMaski = 3;
        BufferedImage in = new BufferedImage(600,600,1);
        int x = 100;
        int y = 100;
        int[][] expResult = {{0,0,0},{0,0,0},{0,0,0}};
        int[][] result = helpers.zwrocMaske(rozmiarMaski, in, x, y);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
