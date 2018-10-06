/**
 * Reviewer Node class
 * @author Hisham Juneidi
 * @version 10-01-2018
 */
public class ReviewerNode {
    private String value;
    private ReviewerNode bottom;
    private ReviewerNode top;
    private RatingNode next;
    private int index;
    
    /**
     * constructor for reviewer node
     * @param name name of reviewer
     * @param i index of reviewer
     */
    public ReviewerNode(String name, int i) {
        value = name;
        index = i;
    }
    
    /**
     * gets name of reviewer
     * @return name of reviewer
     */
    public String value() {
        return value;
    }
    
    /**
     * sets bottom of node
     * @param b node added to bottom 
     * of current node
     */
    public void setBottom(ReviewerNode b) {
        bottom = b;
    }
    
    /**
     * gets bottom of node
     * @return bottom of node
     */
    public ReviewerNode bottom() {
        return bottom;
    }

    public void setTop(ReviewerNode t) {
        top = t;
    }

    public ReviewerNode top() {
        return top;
    }
    
    /**
     * sets next node
     * @param n node being set
     * to next
     */
    public void setNext(RatingNode n) {
        next = n;
    }
    
    /**
     * gets next node
     * @return next node
     */
    public RatingNode next() {
        return next;
    }
    
    /**
     * gets reviewer index
     * @return reviewer index
     */
    public int getIndex() {
        return index;
    }
}
