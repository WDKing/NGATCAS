import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Christi Kazakov 
 */
public class TextCommunicationIT {
    
    public TextCommunicationIT() {
        TextCommunication instance = new TextCommunication();
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
     * Test of listener method, of class TextCommunication.
     */
    @Test
    public void testListener() {
        System.out.println("listener");
        TextCommunication instance = new TextCommunication();
        instance.listener();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of receiver method, of class TextCommunication.
     */
    @Test
    public void testReceiver() {
        System.out.println("receiver");
        Object data = new Object();
        TextCommunication instance = new TextCommunication();
        String expResult = data.toString();
        String result = instance.receiver(data);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of send method, of class TextCommunication.
     */
    @Test
    public void testSend() {
        System.out.println("send");
        String message = " ";
        String message2 = "Warning Level 1";
        String message3 = "MayDay MayDay";
        TextCommunication instance =  new TextCommunication();
        instance.send(message);
        instance.send(message2);
        instance.send(message3);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case did not go through");
    }

    /**
     * Test of log method, of class TextCommunication.
     */
    @Test
    public void testLog() {
        Date date = new Date();
        System.out.println("log");
        String log = " ";
        String logWarning = "Warning level 1 ";
        TextCommunication instance =  new TextCommunication();
        instance.log(date.toString() + "  : " + logWarning);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
