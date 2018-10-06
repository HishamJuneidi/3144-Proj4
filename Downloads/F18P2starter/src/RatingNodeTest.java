import student.TestCase;

/**
 * tests rating node
 * @author Hisham Juneidi
 * @version 10-01-2018
 */
public class RatingNodeTest extends TestCase {

    private RatingNode rn;
    
    /**
     * sets up rating node for testing
     */
    public void setUp() {
        rn = new RatingNode("Death Note", "Cliff Shaffer", 6, 0);
    }
    
    /**
     * tests setRating() method
     */
    public void testRating() {
        assertEquals(6, rn.rating());
    }
    
    /**
     * tests movie() method
     */
    public void testMovie() {
        assertEquals("Death Note", rn.movie());
    }
    
    /**
     * tests reviewer() method
     */
    public void testReviewer() {
        assertEquals("Cliff Shaffer", rn.reviewer());
    }
    
    /**
     * tests next() method
     */
    public void testNext() {
        assertNull(rn.next());
        rn.setNext(new RatingNode("The Dark Knight", "John Doe", 10, 1));
        assertNotNull(rn.next());
        assertEquals("The Dark Knight", rn.next().movie());
        assertEquals("John Doe", rn.next().reviewer());
        assertEquals(10, rn.next().rating());
        assertEquals(1, rn.next().reviewerIndex());
    }
    
    /**
     * tests setNext() method
     */
    public void testSetNext() {
        assertNull(rn.next());
        rn.setNext(new RatingNode("The Dark Knight", "John Doe", 10, 1));
        assertNotNull(rn.next());
        assertEquals("The Dark Knight", rn.next().movie());
        assertEquals("John Doe", rn.next().reviewer());
        assertEquals(10, rn.next().rating());
        assertEquals(1, rn.next().reviewerIndex());
    }
    
    /**
     * tests bottom() method
     */
    public void testBottom() {
        assertNull(rn.bottom());
        rn.setBottom(new RatingNode("The Dark Knight", "John Doe", 10, 1));
        assertNotNull(rn.bottom());
        assertEquals("The Dark Knight", rn.bottom().movie());
        assertEquals("John Doe", rn.bottom().reviewer());
        assertEquals(10, rn.bottom().rating());
        assertEquals(1, rn.bottom().reviewerIndex());
    }
    
    /**
     * tests setBottom() method
     */
    public void testSetBottom() {
        assertNull(rn.bottom());
        rn.setBottom(new RatingNode("The Dark Knight", "John Doe", 10, 1));
        assertNotNull(rn.bottom());
        assertEquals("The Dark Knight", rn.bottom().movie());
        assertEquals("John Doe", rn.bottom().reviewer());
        assertEquals(10, rn.bottom().rating());
        assertEquals(1, rn.bottom().reviewerIndex());
    }
    
    /**
     * tests reviewerIndex() method
     */
    public void testReviewerIndex() {
        assertEquals(0, rn.reviewerIndex());
    }
}
