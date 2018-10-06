/**
 * Matrix keeping track of movies, reviewers, and ratings
 * @author diaajuneidi
 *
 */
public class SparseMatrix {
    private MovieNode movies;
    private ReviewerNode reviewers;
    private int numReviewers;
    
    /**
     * constructor for sparse matrix
     */
    SparseMatrix() {
        numReviewers = 0;
    }
    
    /**
     * adds movie to matrix
     * @param movieName name of movie being added
     * @return index where movie was added
     */
    public MovieNode addMovie(String movieName) {
        MovieNode curr = movies;
        if (curr == null) {
            movies = new MovieNode(movieName);
            return movies;
        }
        int index = this.searchMovie(movieName);
        if (index != -1) {
            return null;
        }
        while (curr.next() != null) {
            curr = curr.next();
        }
        MovieNode newMovie = new MovieNode(movieName);
        curr.setNext(newMovie);
        return newMovie;
    }
    
    /**
     * gets pointer to movie linked list
     * @return pointer to movie linked list
     */
    public MovieNode getMovies() {
        return movies;
    }
    
    /**
     * gets pointer to reviewer linked list
     * @return pointer to reviewer linked list
     */
    public ReviewerNode getReviewers() {
        return reviewers;
    }
    
    /**
     * adds reviewer to sparse matrix
     * @param name name of reviewer
     * @return index where reviewer was added
     */
    public ReviewerNode addReviewer(String name) {
        ReviewerNode curr = reviewers;
        if (curr == null) {
            reviewers = new ReviewerNode(name, numReviewers);
            numReviewers++;
            return reviewers;
        }
        int index = this.searchReviewer(name);
        if (index != -1) {
            return null;
        }
        while (curr.bottom() != null) {
            curr = curr.bottom();
        }
        ReviewerNode newReviewer = new ReviewerNode(name, numReviewers);
        curr.setBottom(newReviewer);
        numReviewers++;
        return newReviewer;
    }
    
    /**
     * deletes movie from matrix
     * @param movieName name of movie being deleted
     */
    public void deleteMovie(String movieName) {
        int index = this.searchMovie(movieName);
        if (index != -1) {
            MovieNode movie = this.movies;
            if (movie.value().equals(movieName)) {
                this.movies = movie.next();
                movie.setBottom(null);
            } 
            else {
                while (!movie.next().value().equals(movieName)) {
                    movie = movie.next();
                }
                MovieNode temp = movie.next().next();
                movie.next().setBottom(null);
                movie.next().setNext(null);
                movie.setNext(temp);
            }
            ReviewerNode critic = this.reviewers;
            while (critic != null) {
                RatingNode rating = critic.next();
                if (rating != null) {
                    if (rating.movie().equals(movieName)) {
                        RatingNode temp = rating.next();
                        rating.setNext(null);
                        rating.setBottom(null);
                        critic.setNext(temp);
                    } 
                    else {
                        while (rating.next() != null && 
                                !rating.next().movie().equals(movieName)) {
                            rating = rating.next();
                        }
                        if (rating.next() != null) {
                            RatingNode temp = rating.next().next();
                            rating.next().setNext(null);
                            rating.next().setBottom(null);
                            rating.setNext(temp);
                        }
                    }
                }
                critic = critic.bottom();
            }
        }

    }
    
    /**
     * deletes reviewer from matrix
     * @param name name of reviewer being removed
     */
    public void deleteReviewer(String name) {
        int index = this.searchReviewer(name);
        if (index != -1) {
            ReviewerNode reviewer = this.reviewers;
            if (reviewer.value().equals(name)) {
                this.reviewers = reviewer.bottom();
                reviewer.setNext(null);
            } 
            else {
                while (!reviewer.bottom().value().equals(name)) {
                    reviewer = reviewer.bottom();
                }
                ReviewerNode temp = reviewer.bottom().bottom();
                reviewer.bottom().setBottom(null);
                reviewer.bottom().setNext(null);
                reviewer.setBottom(temp);
            }
            MovieNode movie = this.movies;
            while (movie != null) {
                RatingNode rating = movie.bottom();
                if (rating != null) {
                    if (rating.reviewer().equals(name)) {
                        RatingNode temp = rating.bottom();
                        rating.setNext(null);
                        rating.setBottom(null);
                        movie.setBottom(temp);
                    } 
                    else {
                        while (rating.bottom() != null && 
                                !rating.bottom().reviewer().equals(name)) {
                            rating = rating.bottom();
                        }
                        if (rating.bottom() != null) {
                            RatingNode temp = rating.bottom().bottom();
                            rating.bottom().setBottom(null);
                            rating.bottom().setNext(null);
                            rating.setBottom(temp);
                        }
                    }
                }
                movie = movie.next();
            }
        }
    }
    
    /**
     * searches for movie in matrix
     * @param movieName name of movie being searched
     * @return index where movie was found
     * or -1 if movie not in matrix
     */
    public int searchMovie(String movieName) {
        int output = 0;
        MovieNode curr = movies;
        while (curr != null) {
            if (curr.value().equals(movieName)) {
                return output;
            }
            output++;
            curr = curr.next();
        }
        return -1;
    }
    
    /**
     * searches for reviewer in matrix
     * @param name name of reviewer being searched
     * @return index where reviewer was found
     * or -1 if reviewer not in matrix
     */
    public int searchReviewer(String name) {
        int output = 0;
        ReviewerNode curr = reviewers;
        while (curr != null) {
            if (curr.value().equals(name)) {
                return output;
            }
            output++;
            curr = curr.bottom();
        }
        return -1;
    }
    
    /**
     * adds rating to matrix
     * @param movie movie being rated
     * @param reviewer name of person who reviewed movie
     * @param rating rating given to movie
     */
    public void addRating(MovieNode mn, ReviewerNode rn, int rating) {
        //int movieIndex = this.searchMovie(movie);
        //int reviewerIndex = this.searchReviewer(reviewer);
        //if (movieIndex == -1) {
        //    movieIndex = this.addMovie(movie);
        //}
        //if (reviewerIndex == -1) {
        //    reviewerIndex = this.addReviewer(reviewer);
        //}
        MovieNode currMovie = movies;
        ReviewerNode currReviewer = reviewers;
        while (!currMovie.value().equals(mn.value())) {
            currMovie = currMovie.next();
        }

        while (!currReviewer.value().equals(rn.value())) {
            currReviewer = currReviewer.bottom();
        }
        RatingNode r = new RatingNode(mn.value(), rn.value(), 
                rating, currReviewer.getIndex());
        if (currMovie.bottom() == null) {
            currMovie.setBottom(r);
        } 
        else {
            RatingNode curr = currMovie.bottom();
            if (curr.reviewerIndex() > r.reviewerIndex()) {
                r.setBottom(curr);
                currMovie.setBottom(r);
                //return;
            }
            else if (curr.reviewerIndex() == r.reviewerIndex()) {
                curr.setRating(rating);
                //return;
            }
            // int i = 0;
            else {
	            while (curr.bottom() != null && 
	                    curr.bottom().reviewerIndex() < r.reviewerIndex()) {
	                if (curr.bottom().reviewerIndex() == r.reviewerIndex()) {
	                    curr.bottom().setRating(rating);
	                    break;
	                }
	                curr = curr.bottom();
	                // i++;
	            }
	            
	            if (curr.bottom() == null || curr.bottom().reviewerIndex() > r.reviewerIndex()) {
	            		r.setBottom(curr.bottom());
	            		curr.setBottom(r);
	            }
            }
        }
        if (currReviewer.next() == null) {
            currReviewer.setNext(r);
        } 
        else {
            RatingNode curr = currReviewer.next();
            if (curr.movie().equals(r.movie())) {
                curr.setRating(rating);
                //return;
            }
            else {
	            
	            while (curr.next() != null && !curr.next().movie().equals(mn.value())) {
	            		if (curr.next().movie() == r.movie()) {
	                    curr.next().setRating(rating);
	                    break;
	                }
	                curr = curr.next();
	                
	            }
	            if (curr.next() == null || 
	            		curr.next().reviewerIndex() > r.reviewerIndex()) {
	            		r.setNext(curr.next());
	            		curr.setNext(r);
	            }
            }
        }
    }
    
    /**
     * prints out all ratings for a given movie
     * @param movie movie for which ratings must
     * be listed
     */
    public void listMovie(String movie) {
        int index = this.searchMovie(movie);
        if (index == -1) {
            System.out.print("Cannot list, movie |" + movie + "| ");
            System.out.println("not found in the database.");
        } 
        else {
            System.out.println("Ratings for movie |" + movie + "|:");
            MovieNode curr = movies;
            while (!curr.value().equals(movie)) {
                curr = curr.next();
            }
            RatingNode r = curr.bottom();
            while (r != null) {
                System.out.println(r.reviewer() + ": " + r.rating());
                r = r.bottom();
            }
        }
    }
    
    /**
     * prints out all ratings given by a 
     * particular reviewer
     * @param reviewer reviewer for which ratings
     * must be printed
     */
    public void listReviewer(String reviewer) {
        int index = this.searchReviewer(reviewer);
        if (index == -1) {
            System.out.print("Cannot list, reviewer |" + reviewer + "| ");
            System.out.println("not found in the database.");
        } 
        else {
            System.out.println("Ratings for reviewer |" + reviewer + "|:");
            ReviewerNode curr = reviewers;
            while (!curr.value().equals(reviewer)) {
                curr = curr.bottom();
            }
            RatingNode r = curr.next();
            while (r != null) {
                System.out.println(r.movie() + ": " + r.rating());
                r = r.next();
            }
        }
    }
    
    /**
     * prints out all ratings for all movies
     */
    public void printRating() {
        ReviewerNode r = this.reviewers;
        MovieNode m = this.movies;

        if (r == null || m == null) {
            System.out.println("There are no ratings in the database");
        } 
        else {
            while (r != null) {
                System.out.println(r.value() + ": " + r.getIndex());
                r = r.bottom();
            }

            while (m != null) {
                RatingNode rating = m.bottom();
                System.out.print(m.value() + ":");
                while (rating != null) {
                    System.out.print(" " + rating.reviewerIndex() + 
                            ":" + rating.rating());
                    rating = rating.bottom();
                }
                System.out.println();
                m = m.next();
            }
        }
    }
}
