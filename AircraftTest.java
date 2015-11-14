/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;


/**
 *
 * @author Lauren
 */
public class AircraftTest {
    
   
    
    @Test
    public void testConstructor() {
        String id = "A123";
        double[] location = {0.0,0.0,0.0};
        double[] heading = {0.0,0.0,0.0};
        int warningLevel=0;
        Aircraft a = new Aircraft(id,location, heading, warningLevel );
        assertTrue(id.equals(a.getId()));
        assertTrue(Arrays.equals(a.getLocation(), location));
        assertTrue(Arrays.equals(a.getHeading(),heading));
        assertTrue(a.getWarningLevel()==warningLevel);
    }
   
    
}
