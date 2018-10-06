import student.TestCase;

/**
 * tests sparse matrix
 * @author Hisham Juneidi
 * @version 10-01-2018
 */
public class SparseMatrixTest extends TestCase {

    private SparseMatrix sm;
    
    /**
     * sets up sparse matrix for testing
     */
    public void setUp() {
        sm = new SparseMatrix();
    }
    
    /**
     * tests the addMovie() method
     */
    public void testAddMovie() {
        assertEquals(-1, sm.searchMovie("Batman Begins"));
        sm.addMovie("Batman Begins");
        
        //System.out.println(sm.getMovies().next().value());
        sm.addMovie("Batman Begins");
        //assertNull(sm.getMovies().next());
        assertEquals(0, sm.searchMovie("Batman Begins"));
        assertEquals(-1, sm.searchMovie("Death Note"));
        sm.addMovie("Death Note");
        assertEquals(1, sm.searchMovie("Death Note"));
    }
    
    /**
     * tests addReviewer() method
     */
    public void testAddReviewer() {
        assertEquals(-1, sm.searchReviewer("Cliff Shaffer"));
        sm.addReviewer("Cliff Shaffer");
        sm.addReviewer("Cliff Shaffer");
        assertEquals(0, sm.searchReviewer("Cliff Shaffer"));
        assertEquals(-1, sm.searchReviewer("John Doe"));
        sm.addReviewer("John Doe");
        assertEquals(1, sm.searchReviewer("John Doe"));
        sm.deleteReviewer("Cliff Shaffer");
        assertEquals(-1, sm.searchReviewer("Cliff Shaffer"));
        sm.addReviewer("Cliff Shaffer");
        assertEquals(1, sm.searchReviewer("Cliff Shaffer"));
    }
    
    /**
     * tests deleteMovie() method
     */
    public void testDeleteMovie() {
        sm.deleteMovie("Batman Begins");
        assertEquals(-1, sm.searchMovie("Batman Begins"));
        sm.addMovie("Batman Begins");
        sm.addMovie("The Dark Knight");
        sm.addMovie("Mad Max");
        assertEquals(0, sm.searchMovie("Batman Begins"));
        sm.addMovie("Death Node");
        assertEquals(3, sm.searchMovie("Death Node"));
        sm.addMovie("Naruto");
        assertEquals(4, sm.searchMovie("Naruto"));
//        sm.addRating("The Dark Knight", "John Doe", 10);
//        sm.addRating("Mad Max", "Tom Hardy", 10);
//        sm.addRating("The Dark Knight", "Cliff Shaffer", 8);
//        sm.addRating("Naruto", "Sasuke Uchiha", 8);
//        sm.addRating("Death Note", "John Doe", 5);
        sm.deleteMovie("Death Note");
        sm.deleteMovie("Batman Begins");
        sm.deleteMovie("Naruto");
        assertEquals(-1, sm.searchMovie("Death Note"));
        assertEquals(-1, sm.searchMovie("Naruto"));
        assertEquals(-1, sm.searchMovie("Batman Begins"));
        sm.deleteMovie("The Dark Knight");
        sm.deleteMovie("Mad Max");
        
    }
    
    /**
     * tests deleteReviewer() method
     */
    public void testDeleteReviewer() {
        sm.deleteReviewer("Cliff Shaffer");
        assertEquals(-1, sm.searchReviewer("Cliff Shaffer"));
        sm.addReviewer("Cliff Shaffer");
        assertEquals(0, sm.searchReviewer("Cliff Shaffer"));
        sm.addReviewer("John Doe");
        assertEquals(1, sm.searchReviewer("John Doe"));
        sm.addReviewer("Sasuke Uchiha");
        assertEquals(2, sm.searchReviewer("Sasuke Uchiha"));
        sm.addReviewer("Tom Hardy");
        sm.addReviewer("Gordon Ramsey");
//        sm.addRating("Death Note", "Gordon Ramsey", 4);
//        sm.addRating("Death Note", "John Doe", 5);
//        sm.addRating("Death Note", "Cliff Shaffer", 6);
//        sm.addRating("The Dark Knight", "Gordon Ramsey", 8);
        
        sm.deleteReviewer("Tom Hardy");
        sm.deleteReviewer("John Doe");
        sm.deleteReviewer("Sasuke Uchiha");
        sm.deleteReviewer("Cliff Shaffer");
        sm.deleteReviewer("Gordon Ramsey");
        assertEquals(-1, sm.searchReviewer("Cliff Shaffer"));
        assertEquals(-1, sm.searchReviewer("John Doe"));
        assertEquals(-1, sm.searchReviewer("Sasuke Uchiha"));
    }
    
    /**
     * tests searchMovie() method
     */
    public void testSearchMovie() {
        assertEquals(-1, sm.searchMovie("Death Note"));
        sm.addMovie("Death Note");
        sm.addMovie("The Dark Knight");
        sm.addMovie("The Incredibles");
        assertEquals(-1, sm.searchMovie("The Dark Knight Rises"));
        assertEquals(0, sm.searchMovie("Death Note"));
        assertEquals(1, sm.searchMovie("The Dark Knight"));
        assertEquals(2, sm.searchMovie("The Incredibles"));
    }
    
    /**
     * tests searchReviewer() method
     */
    public void testSearchReviewer() {
        assertEquals(-1, sm.searchReviewer("Cliff Shaffer"));
        sm.addReviewer("Cliff Shaffer");
        sm.addReviewer("John Doe");
        sm.addReviewer("Sasuke Uchiha");
        assertEquals(-1, sm.searchReviewer("Saitama"));
        assertEquals(0, sm.searchReviewer("Cliff Shaffer"));
        assertEquals(1, sm.searchReviewer("John Doe"));
        assertEquals(2, sm.searchReviewer("Sasuke Uchiha"));
    }
    
    /**
     * tests addRating() method
     */
    public void testAddRating() {
//        assertEquals(-1, sm.searchMovie("Death Note"));
//        sm.addRating("Death Note", "Cliff Shaffer", 10);
//        sm.addRating("Death Note", "Cliff Shaffer", 5);
//        sm.addRating("The Dark Knight", "Cliff Shaffer", 8);
//        assertEquals(0, sm.searchMovie("Death Note"));
//        assertEquals(0, sm.searchReviewer("Cliff Shaffer"));
//        sm.addRating("The Dark Knight", "Cliff Shaffer", 10);
//        sm.addRating("Naruto", "John Doe", 8);
//        sm.addRating("Death Note", "John Doe", 5);
//        sm.addRating("The Dark Knight", "John Doe", 10);
//        sm.addRating("The Dark Knight", "John Doe", 9);
//        sm.addRating("The Good, The Bad, The Ugly", "Darth Vader", 4);
//        sm.deleteReviewer("Darth Vader");
//        assertEquals(-1, sm.searchReviewer("Darth Vader"));
//        sm.addRating("The Good, The Bad, The Ugly", "Darth Vader", 4);
//        assertEquals(2, sm.searchReviewer("Darth Vader"));
    }
    
    /**
     * tests listMovie() method
     */
    public void testListMovie() {
            assertNull(sm.getReviewers());
    }
    
    /**
     * tests listReviewer() method
     */
    public void testListReviewer() {
            assertNull(sm.getMovies());
    }
    
    /**
     * tests printRating() method
     */
    public void testPrintRating() {
        assertNull(sm.getMovies());
        sm.printRating();
    }
}
