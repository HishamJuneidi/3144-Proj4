/**
 * arrayRecord file to be inserteded in slots in the hashTable
 * 
 * @author Hisham Juneidi <hishamj6>, Rishabh Anand <risha97>
 * @version (2018-10-1)
 **/
public class ArrayRecord {
    private String name;
    // private String movie;
    private int size;
    private int startingIndex;
    private MovieNode m;
    private ReviewerNode r;
    private boolean isMovie;
    // private int rating;

    /**
     * 
     * @param name
     *            name of record
     */
    public ArrayRecord(String name, boolean movie/* , int intRating */) {
        this.name = name;
        this.isMovie = movie;
        // rating = intRating;
    }

    /**
     * get name
     * 
     * @return get name
     */
    public String getName() {
        return this.name;
    }
    /*
     * /**
     * 
     * @return rating
     */
    /*
     * public int geRating() { return rating; } public void setRating( int
     * changeRating) { rating = changeRating; }
     */

    /**
     * 
     * @return size
     */

    public int getSize() {
        return size;
    }

    /**
     * 
     * @param s
     *            change size
     */
    public void setSize(int s) {
        size = s;
    }

    /**
     * 
     * @param s
     *            set index
     */
    public void setStartingIndex(int s) {
        startingIndex = s;
    }

    /**
     * 
     * @return index
     */
    public int getStartingIndex() {
        return startingIndex;
    }
    
    public void setMovie(MovieNode movie) {
    		this.m = movie;
    }
    
    public MovieNode movie() {
    		return this.m;
    }
    
    
    public void setReviewer(ReviewerNode reviewer) {
    		this.r = reviewer;
    }
    
    public ReviewerNode reviewer() {
    		return this.r;
    }
    
    
    public boolean isMovie() {
    		return this.isMovie;
    }
}
