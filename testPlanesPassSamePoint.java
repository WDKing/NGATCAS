import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test when planes pass through the same point, but avoid collision.
 *
 * @author  William King
 * @author  Jason Dixie
 * @author  Christi Kazakov
 * @author  Shelley King
 * @author  Zachery Kuhns
 * @author  Lauren Malone
 * @author  Adam Miller
 * @author  Casey Monson
 * @version 07 Nov 15 - 1453
 */
public class testPlanesPassSamePoint
{
    /**
     * Default constructor for test class testPlanesPassSamePoint
     */
    public testPlanesPassSamePoint()
    {
    }

    /**
     * Test two planes in various directions of vector
     */
    @Test
    public void testSameAltitude(){
        ADSBInterface ADSB1 = new ADSBInterface({});
        Aircraft plane1A = new Aircraft("1A",{10.0,10.0,20.0},{0.0,0.0,-5.0});
        Aircraft plane1B = new Aircraft("1B",{0.0,0.0,10.0},{10.0,10.0,10.0});
        ADSB1.addAircraft(plane1A);
        ADSB1.addAircraft(plane1B);
        
        // The design said in, so if it is called with this, it should put out the 
        // correct warning level
        <Name of Our Program>(ADSB1);
    }
    
    /**
     * Test two planes, one climbing through the path of the other
     */
    @Test
    public void testSameAltitude(){
        ADSBInterface ADSB2 = new ADSBInterface({});
        Aircraft plane2A = new Aircraft("2A",{0.0,0.0,1000.0},{20.0,0.0,0.0});
        Aircraft plane2B = new Aircraft("2B",{0.0,0.0,500.0},{15.0,0,250.0});
        ADSB2.addAircraft(plane2A);
        ADSB2.addAircraft(plane2B);
        
        // The design said in, so if it is called with this, it should put out the 
        // correct warning level
        <Name of Our Program>(ADSB2);
    }
     */
        
    /**
     * Test two planes, on a level plane
     */
    @Test
    public void testSameAltitude(){
        ADSBInterface ADSB3 = new ADSBInterface({});
        Aircraft plane3A = new Aircraft("3A",{0.0,0.0,1000.0},{20.0,-5.0,0.0});
        Aircraft plane3B = new Aircraft("3B",{0.0,0.0,1000.0},{0.0,-10.0,0.0});
        ADSB3.addAircraft(plane3A);
        ADSB3.addAircraft(plane3B);
        
        // The design said in, so if it is called with this, it should put out the 
        // correct warning level
        <Name of Our Program>(ADSB3);
    }
}
