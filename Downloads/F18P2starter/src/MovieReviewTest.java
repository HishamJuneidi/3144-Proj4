import student.TestCase;

/**
 * @author {Hisham Juneidi}
 * @version {10-01-2018}
 */
public class MovieReviewTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing Here
    }

    /**
     * Get code coverage of the class declaration.
     */
    public void testRInit() {
        MovieReview recstore = new MovieReview();
        assertNotNull(recstore);
        String[] args = {"6", "P2SampleInput.txt"};
        MovieReview.main(args);
    }
}
