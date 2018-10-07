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
		int index = 0;
		if (curr == null) {
			movies = new MovieNode(movieName);
			movies.setIndex(index);
			return movies;
		}
		while (curr.next() != null) {
			curr = curr.next();
			index++;
		}
		MovieNode newMovie = new MovieNode(movieName);
		newMovie.setIndex(index);
		curr.setNext(newMovie);
		newMovie.setPrevious(curr);
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
		while (curr.bottom() != null) {
			curr = curr.bottom();
		}
		ReviewerNode newReviewer = new ReviewerNode(name, numReviewers);
		curr.setBottom(newReviewer);
		newReviewer.setTop(curr);
		numReviewers++;
		return newReviewer;
	}

	/**
	 * deletes movie from matrix
	 * @param movieName name of movie being deleted
	 */
	public void deleteMovie(ArrayRecord movieName) {
		MovieNode mn = movieName.movie();
		MovieNode prev = mn.getPrevious();
		if (prev != null) {
			prev.setNext(mn.next());
		}
		if (mn.next() != null) {
			mn.next().setPrevious(prev);
			mn.setNext(null);
		}
		mn.setPrevious(null);
		RatingNode rating = mn.bottom();
		while (rating != null) {
			RatingNode previous = rating.previous();
			if (previous != null) {
				previous.setNext(rating.next());
			}
			if (rating.next() != null) {
				rating.next().setPrevious(previous);
			}
			rating.setPrevious(null);
			rating.setNext(null);
			rating = rating.bottom();
		}
		/*int index = this.searchMovie(movieName);
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
        }*/

	}

	/**
	 * deletes reviewer from matrix
	 * @param name name of reviewer being removed
	 */
	public void deleteReviewer(ArrayRecord name) {
		ReviewerNode rn = name.reviewer();
		ReviewerNode top = rn.top();
		if (top != null) {
			top.setBottom(rn.bottom());
		}
		if (rn.bottom() != null) {
			rn.bottom().setTop(top);
			rn.setBottom(null);
		}
		rn.setTop(null);
		RatingNode rating = rn.next();
		while (rating != null) {
			RatingNode t = rating.top();
			if (t != null) {
				t.setBottom(rating.bottom());
			}
			if (rating.bottom() != null) {
				rating.bottom().setTop(t);
			}
			rating.setTop(null);
			rating.setBottom(null);
			rating = rating.next();
		}
		/*int index = this.searchReviewer(name);
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
        }*/
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
		RatingNode r = new RatingNode(mn.value(), rn.value(), 
				rating, rn.getIndex());
		if (mn.bottom() == null) {
			mn.setBottom(r);
		} 
		else {
			RatingNode curr = mn.bottom();
			if (curr.reviewerIndex() > r.reviewerIndex()) {
				r.setBottom(curr);
				curr.setTop(r);
				mn.setBottom(r);
			}
			else if (curr.reviewerIndex() == r.reviewerIndex()) {
				curr.setRating(rating);
			}
			else {
				while (curr.bottom() != null && 
						curr.bottom().reviewerIndex() < r.reviewerIndex()) {
					if (curr.bottom().reviewerIndex() == r.reviewerIndex()) {
						curr.bottom().setRating(rating);
						break;
					}
					curr = curr.bottom();
				}

				if (curr.bottom() == null || curr.bottom().reviewerIndex() > r.reviewerIndex()) {
					r.setBottom(curr.bottom());
					if (r.bottom() != null) {
						r.bottom().setTop(r);
					}
					curr.setBottom(r);
					r.setTop(curr);
				}
			}
		}
		int index = 0;
		if (rn.next() == null) {
			rn.setNext(r);
			return;
		} 
		else if (index == mn.index()) {
			RatingNode next = rn.next();
			r.setNext(next);
			rn.setNext(r);
			if (next != null) {
				next.setPrevious(r);
			}
			return;
		}
		else {
			RatingNode curr = rn.next();
			if (curr.movie().equals(r.movie())) {
				curr.setRating(rating);
				return;
			}
			else {
				index = 1;
				while (curr.next() != null && index < mn.index()) {

					if (curr.next().movie() == r.movie()) {
						curr.next().setRating(rating);
						return;
					}
					curr = curr.next();
					index++;
					if (index == mn.index()) {
						RatingNode next = curr.next();
						r.setNext(next);
						curr.setNext(r);
						r.setPrevious(curr);
						if (next != null) {
							next.setPrevious(r);
						}
						return;
					}
				}
				if (curr.next() != null) {
					if (curr.next().movie() == r.movie()) {
						curr.next().setRating(rating);
						return;
					}
				}
				RatingNode next = curr.next();
				r.setNext(next);
				curr.setNext(r);
				r.setPrevious(curr);
				if (next != null) {
					next.setPrevious(r);
				}
				return;

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

