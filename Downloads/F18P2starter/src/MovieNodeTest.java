import student.TestCase;

/**
 * 
 * @author hisham Juneidi
 * @version 10-01-2018
 */
public class MovieNodeTest extends TestCase {

    private MovieNode mn;

    /**
     * set up for creating obj
     */
    public void setUp() {
        mn = new MovieNode("The Dark Knight");
    }

    /**
     * test value
     */
    public void testValue() {
        assertEquals("The Dark Knight", mn.value());
    }

    /**
     * test setNext node movie
     */
    public void testSetNext() {
        assertNull(mn.next());
        mn.setNext(new MovieNode("Death Note"));
        assertNotNull(mn.next());
        assertEquals("Death Note", mn.next().value());
    }

    /**
     * test next
     */
    public void testNext() {
        assertNull(mn.next());
        mn.setNext(new MovieNode("Death Note"));
        assertNotNull(mn.next());
        assertEquals("Death Note", mn.next().value());
    }

    /**
     * test setBottom
     */
    public void testSetBottom() {
        assertNull(mn.bottom());
        mn.setBottom(new RatingNode("The Dark Knight", "John Doe", 10, 0));
        assertNotNull(mn.bottom());
        assertEquals("The Dark Knight", mn.bottom().movie());
        assertEquals(10, mn.bottom().rating());
        assertEquals("John Doe", mn.bottom().reviewer());
    }

    /**
     * test bottom
     */
    public void testBottom() {
        assertNull(mn.bottom());
        mn.setBottom(new RatingNode("The Dark Knight", "John Doe", 10, 0));
        assertNotNull(mn.bottom());
        assertEquals("The Dark Knight", mn.bottom().movie());
        assertEquals(10, mn.bottom().rating());
        assertEquals("John Doe", mn.bottom().reviewer());
    }
}
