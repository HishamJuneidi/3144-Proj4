/**
 * create movie node class
 * 
 * @author Hisham Juneidi <hishamj6>
 * @version (2018-10-1)
 **/
public class MovieNode {
    private String value;
    private int index;
    private MovieNode next;
    private MovieNode previous;
    private RatingNode bottom;
    /**
     * 
     * @param name movie name
     */
    public MovieNode(String name) {
        value = name;
    }
    /**
     * 
     * @return value
     */
    public String value() {
        return value;
    }
    /**
     * 
     * @param n set next node
     */
    public void setNext(MovieNode n) {
        next = n;
    }
    /**
     *  
     * @return next movie node 
     */
    public MovieNode next() {
        return next;
    }

    public void setPrevious(MovieNode p) {
        previous = p;
    }

    public MovieNode getPrevious() {
        return previous;
    }
    /**
     * 
     * @param b set bottom node
     */
    public void setBottom(RatingNode b) {
        bottom = b;
    }
    /**
     * 
     * @return get rating node bottom
     */
    public RatingNode bottom() {
        return bottom;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public int index() {
        return this.index;
    }
    
}
