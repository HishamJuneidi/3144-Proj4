import student.TestCase;

/**
 * tests the reviewer node
 * @author Hisham Juneidi
 * @version 10-01-2018
 */
public class ReviewerNodeTest extends TestCase {

    private ReviewerNode rn;
    
    /**
     * sets up reviewer node for testing
     */
    public void setUp() {
        rn = new ReviewerNode("John Doe", 0);
    }
    
    /**
     * tests value() method
     */
    public void testValue() {
        assertEquals("John Doe", rn.value());
    }
    
    /**
     * tests setBottom() method
     */
    public void testSetBottom() {
        assertNull(rn.bottom());
        rn.setBottom(new ReviewerNode("Cliff Shaffer", 1));
        assertNotNull(rn.bottom());
        assertEquals(1, rn.bottom().getIndex());
    }
    
    /**
     * tests bottom() method
     */
    public void testBottom() {
        assertNull(rn.bottom());
        rn.setBottom(new ReviewerNode("Cliff Shaffer", 1));
        assertNotNull(rn.bottom());
        assertEquals(1, rn.bottom().getIndex());
    }
    
    /**
     * tests setNext() method
     */
    public void testSetNext() {
        assertNull(rn.next());
        rn.setNext(new RatingNode("Death Note", "John Doe", 5, rn.getIndex()));
        assertNotNull(rn.next());
        assertEquals(5, rn.next().rating());
    }
    
    /**
     * tests next() method
     */
    public void testNext() {
        assertNull(rn.next());
        rn.setNext(new RatingNode("Death Note", "John Doe", 5, rn.getIndex()));
        assertNotNull(rn.next());
        assertEquals(5, rn.next().rating());
    }
    
    /**
     * tests getIndex() method
     */
    public void testGetIndex() {
        assertEquals(0, rn.getIndex());
    }
}
