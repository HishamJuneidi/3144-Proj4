/**
 * 
 * @author Hisham Juneidi
 * @version 10-01-2018
 */
public class RatingNode {
    
    private int rating;
    private int reviewerIndex;
    private String movie;
    private String reviewer;
    private RatingNode next;
    private RatingNode bottom;
    
    /**
     * constructor for rating node
     * @param m movie name
     * @param r reviewer name
     * @param i rating
     * @param ri reviewer index
     */
    public RatingNode(String m, String r, int i, int ri) {
        movie = m;
        reviewer = r;
        rating = i;
        reviewerIndex = ri;
    }
    
    /**
     * gets rating
     * @return rating 
     */
    public int rating() {
        return rating;
    }
    
    /**
     * changes the rating
     * @param i change rating
     */
    public void setRating(int i) {
        rating = i;
    }
    
    /**
     * gets movie name
     * @return get movie
     */
    public String movie() {
        return movie;
    }
    
    /**
     * gets reviewer name
     * @return return reviewer
     */
    public String reviewer() {
        return reviewer;
    }
    
    /**
     * gets next rating node
     * @return return next
     */
    public RatingNode next() {
        return next;
    }
    
    /**
     * sets next rating node
     * @param n change next
     */
    public void setNext(RatingNode n) {
        next = n;
    }
    
    
    /**
     * gets bottom node
     * @return get bottom 
     */
    public RatingNode bottom() {
        return bottom;
    }
    
    /**
     * sets bottom node
     * @param b change bottom 
     */
    public void setBottom(RatingNode b) {
        bottom = b;
    }
    
    /**
     * gets reviewer index
     * @return return reviewer index
     */
    public int reviewerIndex() {
        return reviewerIndex;
    }

}
