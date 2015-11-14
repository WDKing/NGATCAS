import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class to make sure a collision is not wrongly predicted when a craft
 * will pass over another craft with adequate vertical distance separating them.
 */
public class AltitudeNonCollisionTest {
    /** xyz velocities */
    private static final double[] HEADING1 = {0.0, 550.0, 0.0};
    /** latitude, longitude, and altitude */
    private static final double[] LOCATION1 = {0.0, 0.0, 1000.0};
    /** xyz velocities */
    private static final double[] HEADING2 = {-550.0, 0.0, 0.0};
    /** latitude, longitude, and altitude */
    private static final double[] LOCATION2 = {550.0, 550.0, 2000.0};
    /** latitude, longitude, and altitude */
    private static final double[] LOCATION3 = {550.0, 550.0, 1000.0};

    /** Value for a warning level corresponding to no warning */
    private static final int WARNING_LEVEL_GREEN = 0;


    /** Ownship test object */
    private final Aircraft thisPlane = new Aircraft("thisPlane", HEADING1,
            LOCATION1, WARNING_LEVEL_GREEN);
    /** Other aircraft test object */
    private final Aircraft otherPlaneA = new Aircraft("otherPlane", HEADING2,
            LOCATION2, WARNING_LEVEL_GREEN);
    /** Another aircraft test object */
    private final Aircraft otherPlaneB = new Aircraft("otherPlane", HEADING2,
            LOCATION3, WARNING_LEVEL_GREEN);

    /**
     * Test that no collision is predicted when one plane will pass over another
     * with adequate vertical distance
     */
    @Test
    public void testAltitudeSeparation() {
        System.out.println("testAltitudeSeparation");
        Collision expResult = null; // No collision
        Collision result;
        WarningLevelCalculator wlc = new WarningLevelCalculator();

        result = wlc.detectCollision(thisPlane, otherPlaneA);
        assertEquals(expResult, result);
    }

    /**
     * Test that a collision is predicted when planes have no vertical distance
     * between them and are on a clear collision vector with each other
     */
    @Test
    public void testAltitudeMatch() {
        System.out.println("testAltitudeMatch");
        Collision result;
        WarningLevelCalculator wlc = new WarningLevelCalculator();

        result = wlc.detectCollision(thisPlane, otherPlaneB);
        assertTrue(result != null);
    }
}
